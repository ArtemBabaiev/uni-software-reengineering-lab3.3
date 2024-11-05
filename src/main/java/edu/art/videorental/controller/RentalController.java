package edu.art.videorental.controller;

import edu.art.videorental.dto.FinishRentalRequest;
import edu.art.videorental.dto.StartRentalRequest;
import edu.art.videorental.model.Rental;
import edu.art.videorental.model.Statement;
import edu.art.videorental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/customer/{id}")
    public List<Rental> fetchRentalsForCustomer(@PathVariable String id) {
        return rentalService.getRentalsByCustomerId(id);
    }


    @PostMapping
    public List<Rental> startRental(@RequestBody StartRentalRequest request){
        return rentalService.startRental(request);
    }

    @PutMapping
    public Statement finishRental(@RequestBody FinishRentalRequest request){
        return this.rentalService.finishRental(request);
    }

}
