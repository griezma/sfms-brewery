package griezma.mssc.jms;

import griezma.mssc.jms.config.JmsConfig;
import griezma.mssc.jms.model.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloReceiver {
    private final JmsTemplate jms;

    @JmsListener(destination = JmsConfig.HELLO_QUEUE)
    public void receiveMessage(Email mail, Message message, @Headers MessageHeaders headers) {
        log.debug("receiveMessage: {} {} {}", mail, headers, message);
//        throw new RuntimeException();
    }

    @JmsListener(destination = JmsConfig.HELLO_REPLY_QUEUE)
    public void receiveHelloReplyMessage(Email mail, Message message, @Headers MessageHeaders headers) throws JMSException {
        log.debug("receiveHelloReplyMessage: {} {} {}", mail, headers, message);
        jms.convertAndSend(message.getJMSReplyTo(), mail);
    }
}
