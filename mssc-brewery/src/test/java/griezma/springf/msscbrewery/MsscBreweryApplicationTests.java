package griezma.springf.msscbrewery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import griezma.springf.msscbrewery.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MsscBreweryApplicationTests {
    @Autowired
    MockMvc mvc;

    private static final String base = "http://localhost:8080/";

    @Test
    void contextLoads() {
    }

    @Test
    void shouldAddBeer() throws Exception {
        BeerDto beer = makeTestBeer();
        mvc.perform(post(base + "api/v1/beer")
                .content(beanToJson((beer)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/v1/beer")));

    }

    private BeerDto makeTestBeer() {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Wild Goose")
                .beerStyle("Pale Ale")
                .upc((long) new Random().nextInt())
                .build();
    }

    private static String beanToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}