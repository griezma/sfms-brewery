package griezma.springb.jms.model;

import lombok.*;

import java.util.UUID;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class HelloMessage {
    private UUID id;
    private String message;
}
