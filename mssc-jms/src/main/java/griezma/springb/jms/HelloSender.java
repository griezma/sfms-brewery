package griezma.springb.jms;

import griezma.springb.jms.config.JmsConfig;
import griezma.springb.jms.model.HelloMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloSender {

    private final JmsTemplate jms;
    private final MessageConverter jmsConverter;

    @Scheduled(fixedRate = 2000)
    void sendMessage() {
        log.debug("sendMessage");
        jms.convertAndSend(JmsConfig.HELLO_QUEUE, HelloMessage.builder().id(UUID.randomUUID()).message("Hello, Planet " + System.currentTimeMillis()).build());
    }

    @Scheduled(fixedRate = 2000)
    void sendAndReceiveMessage() throws JMSException {
        log.debug("sendAndReceiveMessage");

        Message received = jms.sendAndReceive(JmsConfig.HELLO_REPLY_QUEUE, session -> {
            HelloMessage content = HelloMessage.builder().id(UUID.randomUUID()).message("Hello, Planet " + System.currentTimeMillis()).build();
            return jmsConverter.toMessage(content, session);
        });
        log.debug("received: {}", jmsConverter.fromMessage(received));
    }
}
