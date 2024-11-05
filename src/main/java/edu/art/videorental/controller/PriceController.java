package edu.art.videorental.controller;

import edu.art.videorental.model.Price;
import edu.art.videorental.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @GetMapping
    public List<Price> getAllPrices() {
        return priceService.getAllPrices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable String id) {
        return priceService.getPriceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Price createPrice(@RequestBody Price price) {
        return priceService.createPrice(price);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable String id, @RequestBody Price priceDetails) {
        try {
            Price updatedPrice = priceService.updatePrice(id, priceDetails);
            return ResponseEntity.ok(updatedPrice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable String id) {
        priceService.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
}
