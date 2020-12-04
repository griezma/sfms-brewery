package griezma.springf.msscbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @Null
    private UUID id;

    @Null
    private OffsetDateTime created;

    @Null
    private OffsetDateTime lastUpdate;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
}
