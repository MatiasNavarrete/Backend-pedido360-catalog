package com.example.ms_pedidos360_catalog.controller;

import com.example.ms_pedidos360_catalog.service.PedidoProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestMensajeController {

    private final PedidoProducerService producerService;

    public TestMensajeController(PedidoProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/enviar")
    public String probarMensaje() {
        producerService.emitirEvento("{\"test\": \"¡Conexión exitosa desde el microservicio a Azure Service Bus!\"}");
        return "Evento disparado. Revisa tu consola de Spring y la métrica en el portal de Azure.";
    }
}
