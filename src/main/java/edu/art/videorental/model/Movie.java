package edu.art.videorental.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("movies")
public class Movie {
    @Id
    private String id;
    private String name;
    private Price price;
}
