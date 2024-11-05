package edu.art.videorental.service;

import edu.art.videorental.dto.FinishRentalRequest;
import edu.art.videorental.dto.StartRentalRequest;
import edu.art.videorental.model.*;
import edu.art.videorental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    private final CustomerService customerService;

    private final MovieService movieService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, CustomerService customerService, MovieService movieService) {
        this.rentalRepository = rentalRepository;
        this.customerService = customerService;
        this.movieService = movieService;
    }

    public List<Rental> startRental(StartRentalRequest request) {
        Customer customer = this.customerService.getCustomerById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + request.getCustomerId()));
        List<Movie> movieList = this.movieService.getMoviesByIds(request.getMovieIds());
        List<Rental> rentals = new ArrayList<>();
        LocalDate nowDateTime = LocalDate.now();
        movieList.forEach(movie -> {
            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setMovie(movie);
            rental.setRentalStart(nowDateTime);
            rentals.add(rental);
        });
        return this.rentalRepository.saveAll(rentals);
    }

    public Statement finishRental(FinishRentalRequest request) {
        Customer customer = this.customerService.getCustomerById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + request.getCustomerId()));

        List<Rental> existingRentals = rentalRepository.findAllById(request.getRentalIds());

        LocalDate nowDateTime = LocalDate.now();
        existingRentals.forEach(rental -> {
            if (!rental.getCustomer().getId().equals(customer.getId())) {
                throw new RuntimeException("Rental with ID " + rental.getId() + " does not belong to customer with ID " + customer.getId());
            }
            if (rental.getRentalFinish() != null) {
                throw new RuntimeException("Rental with ID " + rental.getId() + " has already been finished");
            }
            rental.setRentalFinish(nowDateTime);
        });

        Statement statement = getStatement(customer, existingRentals);
        statement.calculateTotalCharge();
        rentalRepository.saveAll(existingRentals);

        return statement;
    }

    private Statement getStatement(Customer customer, List<Rental> rentals) {
        Statement statement = new Statement();

        rentals.forEach(rental -> {
            statement.addCharge(Statement.Charge.builder()
                    .movieName(rental.getMovie().getName())
                    .charge(calculateCharge(rental)).build()
            );
        });

        statement.setCustomerName(customer.getName());
        return statement;
    }

    private double calculateCharge(Rental rental) {
        Price price = rental.getMovie().getPrice();
        double charge = price.getDefaultPrice();
        if (rental.getDaysRented() > price.getDays()) {
            charge += (rental.getDaysRented() - price.getDays()) * price.getPenaltyPrice();
        }
        return charge;
    }

    public List<Rental> getRentalsByCustomerId(String id) {
        var result = rentalRepository.findAllByCustomer_Id(id);
        return result;
    }
}
