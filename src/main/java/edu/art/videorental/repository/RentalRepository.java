package edu.art.videorental.repository;

import edu.art.videorental.model.Rental;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends MongoRepository<Rental, String> {
    List<Rental> findAllByCustomer_Id(String id);
}
