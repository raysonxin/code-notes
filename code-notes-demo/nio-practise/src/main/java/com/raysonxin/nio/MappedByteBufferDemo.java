package com.raysonxin.nio;

import org.junit.Test;

import java.io.File;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @className: MappedByteBufferDemo.java
 * @author: raysonxin
 * @date: 2020/2/10 10:26 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class MappedByteBufferDemo {

    private final static String CONTENT = "Zero copy implemented by MappedByteBuffer";
    private final static String FILE_NAME = "/mmap.txt";
    private final static String CHARSET = "UTF-8";

    @Test
    public void writeToFileByMappedByteBuffer() {
        Path path = Paths.get(getClass().getResource(FILE_NAME).getPath());
        byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);
            if (mappedByteBuffer != null) {
                mappedByteBuffer.put(bytes);
                mappedByteBuffer.force();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void readFromFileByMappedByteBuffer(){
       Path path=Paths.get(getClass().getResource(FILE_NAME).getPath());
       int length=CONTENT.getBytes(Charset.forName(CHARSET)).length;
       try(FileChannel fileChannel= FileChannel.open(path,StandardOpenOption.READ)){
           MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY,0,length);
           if(mappedByteBuffer!=null){
               byte[] bytes=new byte[length];
               mappedByteBuffer.get(bytes);
               String content=new String(bytes, StandardCharsets.UTF_8);
               System.out.println(content);
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }

}
