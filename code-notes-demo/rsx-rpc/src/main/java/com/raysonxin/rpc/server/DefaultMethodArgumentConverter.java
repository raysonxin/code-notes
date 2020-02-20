package com.raysonxin.rpc.server;

import com.raysonxin.rpc.exception.ArgumentConvertException;
import com.raysonxin.rpc.protocol.serialize.FastJsonSerializer;
import com.raysonxin.rpc.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className: DefaultMethodArgumentConverter.java
 * @author: raysonxin
 * @date: 2020/2/15 5:56 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
@Component
public class DefaultMethodArgumentConverter implements MethodArgumentConverter {

    private final Serializer serializer = FastJsonSerializer.X;

    @Override
    public ArgumentConvertOutput convert(ArgumentConvertInput input) {

        ArgumentConvertOutput output = new ArgumentConvertOutput();
        try {
            //参数为空
            if (null == input.getArguments() || input.getArguments().isEmpty()) {
                output.setArguments(new Object[0]);
                return output;
            }

            List<Class<?>> inputParameterTypes = input.getParameterTypes();
            int size = inputParameterTypes.size();
            if (size > 0) {
                Object[] arguments = new Object[size];
                for (int i = 0; i < size; i++) {
                    ByteBuf byteBuf = (ByteBuf) input.getArguments().get(i);
                    int readableBytes = byteBuf.readableBytes();
                    byte[] bytes = new byte[readableBytes];
                    byteBuf.readBytes(bytes);
                    arguments[i] = serializer.decode(bytes, inputParameterTypes.get(i));
                    byteBuf.release();
                }
                output.setArguments(arguments);
                return output;
            }
            Class<?>[] parameterTypes = input.getMethod().getParameterTypes();
            int len = parameterTypes.length;
            Object[] arguments = new Object[len];
            for (int i = 0; i < len; i++) {
                ByteBuf byteBuf = (ByteBuf) input.getArguments().get(i);
                int readableBytes = byteBuf.readableBytes();
                byte[] bytes = new byte[readableBytes];
                byteBuf.readBytes(bytes);
                arguments[i] = serializer.decode(bytes, parameterTypes[i]);
                byteBuf.release();
            }
            output.setArguments(arguments);
            return output;
        } catch (Exception e) {
            throw new ArgumentConvertException(e);
        }
    }
}
