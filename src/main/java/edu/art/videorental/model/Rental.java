package edu.art.videorental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@Setter
@Document("rentals")
public class Rental {
    @Id
    private String id;
    private Customer customer;
    private Movie movie;
    private LocalDate rentalStart;
    private LocalDate rentalFinish;

    @JsonIgnore
    public long getDaysRented() {
        return DAYS.between(rentalStart, rentalFinish);
    }
}
