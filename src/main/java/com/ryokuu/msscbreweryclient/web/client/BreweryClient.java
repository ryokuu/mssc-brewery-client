package com.ryokuu.msscbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import com.ryokuu.msscbreweryclient.web.model.BeerDto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Slf4j
public class BreweryClient {

    public final String BEER_PATH_V1 = ("/api/v1/beer/");
    private String apihost;
    private final RestTemplate restTemplate;

    
    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid){
        log.info(apihost + BEER_PATH_V1 + uuid.toString());
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public URI savNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }
    
    public void updateBeer(UUID uuid, BeerDto beerDto){
        restTemplate.put(apihost + BEER_PATH_V1 + "/" + uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID uuid){
        restTemplate.delete(apihost + BEER_PATH_V1 + " /" +uuid);
    }

    public void setApihost(String apihost){
        this.apihost = apihost;
    }
}
