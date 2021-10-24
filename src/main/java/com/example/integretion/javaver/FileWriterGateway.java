package com.example.integretion.javaver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

//@MessagingGateway
@EnableIntegration
@MessagingGateway(name = "FileGateway", defaultRequestChannel = "textInChannel")
public interface FileWriterGateway {

    @Gateway(requestChannel = "textInChannel")
    void writeToFile(
            @Header(FileHeaders.FILENAME) String filename,
            String data);

}
