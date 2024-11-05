package edu.art.videorental.dto;

import edu.art.videorental.model.Customer;
import edu.art.videorental.model.Rental;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FinishRentalRequest {
    private String customerId;
    private List<String> rentalIds;
}
