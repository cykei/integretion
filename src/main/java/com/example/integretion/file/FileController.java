package com.example.integretion.file;

import com.example.integretion.file.FileWriterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages={"com.example.integretion.file"})
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

        gateway.writeToFile("hello","my name is c2!");
        return "SUCCESS";
    }
}
