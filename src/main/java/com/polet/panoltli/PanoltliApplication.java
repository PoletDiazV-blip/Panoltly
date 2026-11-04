package com.polet.panoltli;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PanoltliApplication {

    public static void main(String[] args) {
        // Carga el archivo .env de forma segura
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // Inyecta cada variable directamente en las propiedades del sistema de Java
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );

        SpringApplication.run(PanoltliApplication.class, args);
    }
}