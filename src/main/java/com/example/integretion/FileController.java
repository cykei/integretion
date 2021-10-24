package com.example.integretion;

import com.example.integretion.javaver.FileWriterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages={"com.example.integretion.javaver"})
@RestController
public class FileController {

    //@EnableAutoConfiguration
    //@IntegrationComponentScan("com.example.integretion.javaver.FileWriterGateway")

    @Qualifier("FileGateway")
    @Autowired
    private FileWriterGateway gateway;

//   @Autowired
//   public FileController(FileWriterGateway gateway) {
//        this.gateway = gateway;
//    }


    @GetMapping("/")
    public String file() {

        gateway.writeToFile("hello","my name is CYKEI!");
        return "SUCCESS";
    }
}
