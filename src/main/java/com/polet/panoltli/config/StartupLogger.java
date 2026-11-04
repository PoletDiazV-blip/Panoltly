package com.polet.panoltli.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger implements CommandLineRunner {

    private final Environment environment;

    public StartupLogger(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        String port = environment.getProperty("server.port", "8080");
        System.out.println("Panoltli API running at: http://localhost:" + port);
    }
}
