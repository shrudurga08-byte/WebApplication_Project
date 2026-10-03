package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class StaticFileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();

        // Default to index.html if root is requested
        if (path.equals("/")) {
            path = "/index.html";
        }

        // Read file from the classpath (src/main/resources/static)
        InputStream is = getClass().getResourceAsStream("/static" + path);

        if (is == null) {
            String response = "404 Not Found";
            exchange.sendResponseHeaders(404, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
            return;
        }

        // Determine basic content types for the browser
        if (path.endsWith(".html")) exchange.getResponseHeaders().set("Content-Type", "text/html");
        else if (path.endsWith(".css")) exchange.getResponseHeaders().set("Content-Type", "text/css");
        else if (path.endsWith(".js")) exchange.getResponseHeaders().set("Content-Type", "application/javascript");

        exchange.sendResponseHeaders(200, 0);
        
        try (OutputStream os = exchange.getResponseBody()) {
            is.transferTo(os);
        }
        is.close();
    }
}
