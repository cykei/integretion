package com.example.integretion.kafka;

import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestTransformer extends AbstractTransformer {

    @Override
    protected Object doTransform(Message<?> message) {
        String contents = (String)message.getPayload();
        return MessageBuilder.withPayload(contents.toUpperCase()).build();
    }
}
