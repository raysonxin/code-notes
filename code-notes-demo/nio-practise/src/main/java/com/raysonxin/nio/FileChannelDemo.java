package com.raysonxin.nio;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @className: FileChannelDemo.java
 * @author: raysonxin
 * @date: 2020/2/6 12:42 上午
 * @email: raysonxin@163.com
 * @description: https://www.cnblogs.com/ostenant/p/9695183.html
 **/
public class FileChannelDemo {

    public static void main(String[] args) throws Exception {
//        System.out.println("Start to write");
//        testWriteOnFileChannel();
//
//        System.out.println("Start to read");
//        testReadOnFileChannel();
//
//        System.out.println("Start transfrom");
        testTransferFrom();
//        testTransferTo();
    }

    public static void testWriteOnFileChannel() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("nio-practise/src/main/resources/data.txt", "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            byte[] bytes = new String("Java Non-blocking IO").getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(bytes);

            fileChannel.write(buffer);
            //强制刷入磁盘
            fileChannel.force(true);

            buffer.clear();
            fileChannel.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void testReadOnFileChannel() {
        try {
            FileInputStream inputStream = new FileInputStream(new File("nio-practise/src/main/resources/data.txt"));
            FileChannel fileChannel = inputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            //一次读取10个字节
            while (fileChannel.read(byteBuffer) != -1) {
                //写模式 -> 读模式
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
                byteBuffer.clear();
            }
            fileChannel.close();
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void testTransferFrom() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("nio-practise/src/main/resources/from.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("nio-practise/src/main/resources/to.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel, position, count);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int size = toChannel.read(buffer);
            if (size > 0) {
                String data = new String(buffer.array());
                System.out.println(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void testTransferTo() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("nio-practise/src/main/resources/from.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("nio-practise/src/main/resources/to.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();
            fromChannel.transferTo(position, count, toChannel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
