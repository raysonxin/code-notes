package com.raysonxin.rpc.server;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.raysonxin.rpc.protocol.MessageType;
import com.raysonxin.rpc.protocol.RequestMessagePacket;
import com.raysonxin.rpc.protocol.ResponseMessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @className: ServerHandler.java
 * @author: raysonxin
 * @date: 2020/2/15 7:55 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Component
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<RequestMessagePacket> {

    @Autowired
    private MethodMatcher methodMatcher;

    @Autowired
    private MethodArgumentConverter methodArgumentConverter;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessagePacket packet) throws Exception {
        log.info("服务端接收到：{}", packet);
        MethodMatchInput input = new MethodMatchInput();
        input.setInterfaceName(packet.getInterfaceName());
        input.setMethodArgumentSignatures(Optional.ofNullable(packet.getMethodArgumentSignatures())
                .map(Lists::newArrayList).orElse(Lists.newArrayList()));
        input.setMethodName(packet.getMethodName());
        Object[] methodArguments = packet.getMethodArguments();
        input.setMethodArgumentArraySize(null != methodArguments ? methodArguments.length : 0);

        MethodMatchOutput output = methodMatcher.selectOneBestMatchMethod(input);
        log.info("查找目标实现方法成功，目标类：{}，宿主类：{},宿主方法:{}",
                output.getTargetClass(),
                output.getTargetUserClass().getCanonicalName(),
                output.getTargetMethod());

        ArgumentConvertInput convertInput = new ArgumentConvertInput();
        convertInput.setArguments(input.getMethodArgumentArraySize() > 0 ? Lists.newArrayList(methodArguments) : Lists.newArrayList());
        convertInput.setMethod(output.getTargetMethod());
        convertInput.setParameterTypes(output.getParameterTypes());
        ArgumentConvertOutput convertOutput = methodArgumentConverter.convert(convertInput);

        Method targetMethod = output.getTargetMethod();
        ReflectionUtils.makeAccessible(targetMethod);
        Object result = targetMethod.invoke(output.getTarget(), convertOutput.getArguments());

        ResponseMessagePacket response = new ResponseMessagePacket();
        response.setMagicNumber(packet.getMagicNumber());
        response.setVersion(packet.getVersion());
        response.setSerialNumber(packet.getSerialNumber());
        response.setAttachments(packet.getAttachments());
        response.setMessageType(MessageType.RESPONSE);
        response.setErrorCode(200L);
        response.setMessage("Success");
        response.setPayload(JSON.toJSONString(result));
        log.info("服务端输出:{}", JSON.toJSONString(response));
        channelHandlerContext.writeAndFlush(response);
    }
}
