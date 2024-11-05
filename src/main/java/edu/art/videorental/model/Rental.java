package edu.art.videorental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;

@Getter
@Setter
@Document("rentals")
public class Rental {
    @Id
    private String id;
    private Customer customer;
    private Movie movie;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate rentalStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate rentalFinish;

    public long getDaysRented(){
        return Duration.between(rentalStart, rentalFinish).toDays();
    }
}
