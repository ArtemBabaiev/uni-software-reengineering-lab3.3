package edu.art.videorental.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("prices")
public class Price {
    @Id
    private String id;
    private String genre;

    private double defaultPrice;
    private int days;
    private double penaltyPrice;
}
