package edu.art.videorental.dto;

import edu.art.videorental.model.Customer;
import edu.art.videorental.model.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StartRentalRequest {
    private String customerId;
    private List<String> movieIds;
}
