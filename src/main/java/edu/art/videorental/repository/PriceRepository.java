package edu.art.videorental.repository;

import edu.art.videorental.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends MongoRepository<Price, String> {
}
