package griezma.springf.msscbrewery.web.controller;

import griezma.springf.msscbrewery.services.BeerService;
import griezma.springf.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/beer")
public class BeerController {

    private final BeerService beerService;
    private final String baseUrl = "http://localhost:8080/";

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        log.info("getBeer {}", beerId);
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postBeer(@RequestBody BeerDto beer) {
        log.info("postBeer {}", beer);
        BeerDto newbeer = beerService.addBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, baseUrl + "api/v1/beer/" + newbeer.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putBeer(@RequestBody BeerDto beer) {
        beerService.updateBeer(beer);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId) {
        log.info("deleted {}", beerId);
        beerService.deleteBeer(beerId);
    }
}
