package com.org.asean.javase.mina;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

public class MinaServer {

    public static void main(String[] args) {
        try{

            NioSocketAcceptor acceptor =  new NioSocketAcceptor();

            acceptor.setHandler(new MyServerHandler());

            //拦截器，责任链模式  做对象的转换工作
            acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory()));
            acceptor.bind(new InetSocketAddress(9898));

        }catch (Exception e){

        }
    }
}
