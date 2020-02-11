package com.raysonxin.nio;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @className: BufferDemo.java
 * @author: raysonxin
 * @date: 2020/2/8 12:20 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class BufferDemo {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

       // ByteBuffer buffer = ByteBuffer.wrap("abced".getBytes());

        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(buffer.isDirect());
    }

    @Test
    public void test() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String str = "abcde";
        buffer.put(str.getBytes());
        printResult(buffer);

        buffer.flip();
        printResult(buffer);

        byte[] dst = new byte[buffer.limit()];
        System.out.println("------get()--------");
        buffer.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buffer.position());

        buffer.mark();
        System.out.println("------mark()-------");
        buffer.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buffer.position());

        buffer.reset();
        System.out.println("-----reset()-------");
        System.out.println(buffer.position());

        if (buffer.hasRemaining()) {
            System.out.println(buffer.remaining());
        }

        buffer.rewind();
        System.out.println("-----rewind()-------");
        printResult(buffer);

        buffer.clear();
        System.out.println("-----clear()--------");
        printResult(buffer);
    }

    private void printResult(ByteBuffer buffer) {
        System.out.println("pos=" + buffer.position());
        System.out.println("lmt=" + buffer.limit());
        System.out.println("cap=" + buffer.capacity());
    }

    @Test
    public void testAllocate(){
        int size=10240;

        long start=System.nanoTime();
        ByteBuffer buffer=ByteBuffer.allocate(size);
        long stop=System.nanoTime();
        System.out.println(stop-start);

        long start1=System.nanoTime();
        ByteBuffer buffer1=ByteBuffer.allocateDirect(size);
        long stop1=System.nanoTime();
        System.out.println(stop1-start1);
    }

}
