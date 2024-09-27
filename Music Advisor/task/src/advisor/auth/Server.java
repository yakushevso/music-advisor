package advisor.auth;

import advisor.Config;
import advisor.view.Messages;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void getAuthCode() {
        HttpServer server;

        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server.start();

        System.out.println(Messages.USE_LINK + "\n" + Config.getAuthUri());

        server.createContext("/",
                exchange -> {
                    String response = exchange.getRequestURI().getQuery();

                    if (response != null && response.contains("code")) {
                        Config.AUTH_CODE = response.substring(5);
                        response = Messages.SUCCESSFUL_RESPONSE.toString();
                        System.out.println(Messages.CODE_RECEIVED);
                    } else {
                        response = Messages.FAILED_RESPONSE.toString();
                    }

                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }
        );

        System.out.println(Messages.WAITING);

        while (Config.AUTH_CODE == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        server.stop(1);
    }
}
