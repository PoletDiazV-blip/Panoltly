package com.polet.panoltli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Object> bienvenida() {
        Map<String, Object> response = new HashMap<>();
        response.put("app", "Panoltli API");
        response.put("status", "ONLINE");
        response.put("message", "Bienvenido a la API de Panoltli. El servicio está corriendo correctamente.");
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }
}