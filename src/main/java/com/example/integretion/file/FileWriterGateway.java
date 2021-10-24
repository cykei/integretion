package com.example.integretion.file;

import org.springframework.integration.annotation.Gateway;
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
