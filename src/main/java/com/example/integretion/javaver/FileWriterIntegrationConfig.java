package com.example.integretion.javaver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class FileWriterIntegrationConfig {


    // 변환기
    @Bean
    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> upperCaseTransformer() {
        return text -> text.toUpperCase();
    }


    // 파일-쓰기 메시지 핸들러
    // * 지정된 디렉터리(/tmp/files)에 파일을 쓴다. 파일이름은 해당 메시지의 file_name 헤더에 지정된 것을 사용한다.
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("./tmp/files"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }

    // 채널을 별도로 생성하지 않으면 아래의 코드가 자동생성되서 사용된다.
    /*
    @Bean
    public MessageChannel textInChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChannel() {
        return new DirectChannel();
    }
    */

    // DSL 버전
//    @Bean
//    public IntegrationFlow fileWriterFlow() {
//        return IntegrationFlows
//                .from(MessageChannels.direct("textInChannel"))
//                .<String, String>transform(t -> t.toUpperCase())
//                //.channel(MessageChannels.direct("fileWriterChannel"))
//                .handle(Files
//                    .outboundAdapter(new File("/tmp/files"))
//                    .fileExistsMode(FileExistsMode.APPEND)
//                    .appendNewLine(true))
//                .get();
//    }

}