package griezma.springf.msscbrewery.services;

import griezma.springf.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID, BeerDto> beerDb = new ConcurrentHashMap<>();

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerDb.get(beerId);
    }

    @Override
    public BeerDto addBeer(BeerDto beer) {
        BeerDto newBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .build();
        beerDb.put(newBeer.getId(), newBeer);
        return newBeer;
    }

    @Override
    public BeerDto updateBeer(BeerDto beer) {
        beerDb.put(beer.getId(), beer);
        return beer;
    }

    @Override
    public BeerDto deleteBeer(UUID beerId) {
        return beerDb.remove(beerId);
    }
}
