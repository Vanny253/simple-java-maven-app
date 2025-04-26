package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class App {

    private static final String MESSAGE = "Hello from Spark Web Server! Welcome!";

    public App() {}

    public static void main(String[] args) {
        port(8082);  // Listen on port 8082

        // Handle GET request on "/"
        get("/", (req, res) -> MESSAGE);

        // Handle POST request for GitHub webhook
        post("/github-webhook/", (req, res) -> {
            System.out.println("Webhook received:");
            System.out.println(req.body()); // Print the JSON payload from GitHub
            res.status(200);  // Respond with OK
            return "Webhook received!";
        });
    }

    public String getMessage() {
        return MESSAGE;
    }
}
