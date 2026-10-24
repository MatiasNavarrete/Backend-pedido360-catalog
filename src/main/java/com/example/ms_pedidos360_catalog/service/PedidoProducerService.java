package com.example.ms_pedidos360_catalog.service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducerService {

    private final ServiceBusSenderClient senderClient;

    public PedidoProducerService(
            @Value("${spring.cloud.azure.servicebus.connection-string}") String connectionString,
            @Value("${app.mensajeria.cola-pedidos}") String queueName) {

        this.senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
    }

    public void emitirEvento(String mensajeJson) {
        senderClient.sendMessage(new ServiceBusMessage(mensajeJson));
        System.out.println("✅ Mensaje enviado a Azure Service Bus: " + mensajeJson);
    }
}