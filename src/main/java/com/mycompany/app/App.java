package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;

public class App {

    private static final String MESSAGE = "Hello from Spark Web Server! Welcome to the world of Java!";

    public App() {}

    public static void main(String[] args) {
        port(8082);  // Ensures the app is listening on port 8082
        get("/", (req, res) -> MESSAGE);
    }

    public String getMessage() {
        return MESSAGE;
    }
}
