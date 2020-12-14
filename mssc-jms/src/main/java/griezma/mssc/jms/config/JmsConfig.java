package griezma.mssc.jms.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Slf4j
@Configuration
@EnableJms
public class JmsConfig {
    public static final String HELLO_QUEUE = "hello";
    public static final String HELLO_REPLY_QUEUE = "hello-with-reply";

    @Bean
    public MessageConverter jsonMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("x_json_type");
        return converter;
    }
}
