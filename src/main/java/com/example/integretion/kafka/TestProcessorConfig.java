package com.example.integretion.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.support.KafkaHeaders;

import java.io.File;

@Configuration
public class TestProcessorConfig {
    @Value("TEST_TOPIC")
    private String topic;

    @Bean
    public IntegrationFlow testFlow(KafkaConfig kafkaConfig, TestTransformer testTransformer) {
        return IntegrationFlows
                .from(Kafka.messageDrivenChannelAdapter(kafkaConfig.getConsumerFactory(), topic))
                .transform(testTransformer)
                .log(LoggingHandler.Level.INFO, "TEST",
                        m -> "TOPIC[" + m.getHeaders().get(KafkaHeaders.RECEIVED_TOPIC) + "], " +
                                "PARTITION[" + m.getHeaders().get(KafkaHeaders.RECEIVED_PARTITION_ID) + "], " +
                                "OFFSET[" + m.getHeaders().get(KafkaHeaders.OFFSET) + "], " +
                                "PAYLOAD[" + m.getPayload() + "]")
                .handle(Files.outboundAdapter(new File("C:\\Users\\cykei\\Documents\\test"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true)
                        .charset("x-windows-949"))
                .get();
    }

//    @Transformer
//    public Message<String> testTransformer(Message<String> message) {
//        String contents = message.getPayload();
//        return MessageBuilder.withPayload(contents.toUpperCase(Locale.ROOT)).build();
//    }
//    @Bean
//    public Message<String> testTransformer(Message<String> message) {
//       String contents = message.getPayload();
//       return MessageBuilder.withPayload(contents.toUpperCase(Locale.ROOT)).build();
//    }
}
