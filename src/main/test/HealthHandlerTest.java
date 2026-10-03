package com.example;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthHandlerTest{

    @Test
    public void testHealthEndpointReturnsOk()throws Exception{

        //Start a lightweight test server on a random available port
        HttpServer server = HttpServer.create(new InetSocketAddress (0),0);

        //This class don't exist yet! We are designing its usage.
        server.createContext("/api/health",new HealthHandler());
        server.setExecutor(null);
        server.start();
        
        int port = server.getAddress().getPort();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:"+port+"/api/health"))

        .GET()
        .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertEquals("{\"status\":\"UP\"}",response.body());

        server.stop(0);
    }
}