package griezma.mssc.beerservice.web.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data @Builder
public class BeerDto {
    private UUID id;
    private String beerName;
    private BeerStyle beerStyle;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private Long upc;
    private BigDecimal price;
    private Integer quantityOnHand;
}
