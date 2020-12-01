package griezma.springf.msscbrewery.client;

import griezma.springf.msscbrewery.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientApplicationTests {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto newBeer = createBeer("baa", "baaStyle");
        URI location = client.saveNewBear(newBeer);
        String beerId = beerId(location);

        BeerDto result = client.getBeer(UUID.fromString(beerId));

        assertEquals("baa", result.getBeerName());
    }

    @Test
    void canSaveBeer() {
        URI result = client.saveNewBear(createBeer("foo", "fooStyle"));
        System.out.println(result);
        assertNotNull(result);
    }

    private static BeerDto createBeer(String name, String style) {
        return BeerDto.builder().id(UUID.randomUUID()).beerName(name).beerStyle(style).upc(System.currentTimeMillis()).build();
    }

    private static String beerId(URI location) {
        String sUri = location.toString();
        return sUri.substring(sUri.lastIndexOf("/")+1);
    }

}
