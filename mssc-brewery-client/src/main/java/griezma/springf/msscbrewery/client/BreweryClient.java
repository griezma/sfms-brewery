package griezma.springf.msscbrewery.client;

import griezma.springf.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.UUID;

@Slf4j
@Component
public class BreweryClient {

    @Value("${brewery.apihost}")
    private String host = "http://localhost:7777";

    private final String API_PATH_V1 = "/api/v1/beer/";

    private String apiUrl;

    private final RestTemplate rest;

    public BreweryClient(RestTemplateBuilder builder) {
        this.rest = builder.build();
    }

    @PostConstruct
    private void created() {
        this.apiUrl = host + API_PATH_V1;
    }

    public BeerDto getBeer(UUID id) {
        return rest.getForObject(apiUrl + id.toString(), BeerDto.class);
    }

    public URI saveNewBear(BeerDto beer) {
        log.info("url " + host + ", " + apiUrl);
        return rest.postForLocation(apiUrl, beer);
    }
}
