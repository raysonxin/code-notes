// package com.baosiling.chatroom.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @description:
 * @author: wangzhx
 * @create: 2020-01-21 15:09
 */
public class ChatClient {
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    private volatile boolean success = false;

    public String read(SocketChannel channel) throws IOException {
        buffer.clear();
        int count = channel.read(buffer);
        return new String(buffer.array(), 0, count, StandardCharsets.UTF_8);
    }

    public void write(SocketChannel channel, String content) throws IOException {
        buffer.clear();
        buffer.put(content.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        channel.write(buffer);
    }

    public void connect(String ip, int port) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(()->{
            SocketChannel client = null;
            try {
                while(true){
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while(iter.hasNext()){
                        SelectionKey key = iter.next();
                        iter.remove();
                        if(key.isReadable()){
                            client = (SocketChannel) key.channel();
                            String msg = read(client);
                            System.out.println(msg);
                            if(msg.contains("hello")){
                                success = true;
                            }
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                    keys.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(client != null){
                    try {
                        client.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
        readLine(socketChannel);
    }

    private void readLine(SocketChannel socketChannel) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String tmp = null;
        while(true){
            tmp = scanner.nextLine();
            /*if(success){
                break;
            }*/
            if(tmp !=null && !tmp.trim().equals("")){
                write(socketChannel, tmp);
            }
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        try {
            chatClient.connect(args[0],Integer.parseInt(args[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
