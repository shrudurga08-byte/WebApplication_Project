package com.example;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        
        // Create the standard Java HTTP Server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // Register our health endpoint
        server.createContext("/api/health", new HealthHandler());

        // ADD THIS LINE: Register the static file handler for the root context
        server.createContext("/", new StaticFileHandler());
        
        // Start the server with a default executor
        server.setExecutor(null);
        server.start();
        
        System.out.println("Backend server started on http://localhost:" + port);
    }
}
