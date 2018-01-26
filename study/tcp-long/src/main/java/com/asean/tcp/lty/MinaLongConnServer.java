package com.asean.tcp.lty;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaLongConnServer {
    private static final int PORT = 8003;

//    public static void main(String[] args) {
//
//    }
   // public void start()throws IOException {
        public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new MinaLongConnServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.bind(new InetSocketAddress(PORT));
        System.out.println("Listeningon port " + PORT);
    }
}

