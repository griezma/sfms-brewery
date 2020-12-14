package griezma.mssc.jms.model;

import lombok.*;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Email {

    private String to;
    private String body;
}
