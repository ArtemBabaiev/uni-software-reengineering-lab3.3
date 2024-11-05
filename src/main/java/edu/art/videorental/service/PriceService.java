package edu.art.videorental.service;

import edu.art.videorental.model.Price;
import edu.art.videorental.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    public Optional<Price> getPriceById(String id) {
        return priceRepository.findById(id);
    }

    public List<Price> getPricesByIds(List<String> ids) {
        return priceRepository.findAllById(ids);
    }

    public Price createPrice(Price price) {
        return priceRepository.save(price);
    }

    public Price updatePrice(String id, Price priceDetails) {
        return priceRepository.findById(id).map(price -> {
            price.setGenre(priceDetails.getGenre());
            price.setDefaultPrice(priceDetails.getDefaultPrice());
            price.setPenaltyPrice(priceDetails.getPenaltyPrice());
            price.setDays(priceDetails.getDays());
            return priceRepository.save(price);
        }).orElseThrow(() -> new RuntimeException("Price not found with id " + id));
    }

    public void deletePrice(String id) {
        priceRepository.deleteById(id);
    }
}