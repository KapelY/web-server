package com.study;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;


public class Server {
    private int port;
    private String staticResourcesPath;

    public static void main(String[] args) {
        Server server = new Server();
        server.port = 3000;
        server.staticResourcesPath = "src/main/resources/webapp";
        server.start();
    }

    public void start() {
        Logger log = Logger.getLogger(Server.class.getName());

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("----=====<<<<Server started>>>>====----");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    RequestHandler handler = new RequestHandler(reader, writer, staticResourcesPath);
                    handler.handle();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
