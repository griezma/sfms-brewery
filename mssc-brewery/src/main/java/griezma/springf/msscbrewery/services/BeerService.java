package griezma.springf.msscbrewery.services;

import griezma.springf.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto addBeer(BeerDto beer);

    BeerDto updateBeer(BeerDto beer);

    BeerDto deleteBeer(UUID beerId);
}
