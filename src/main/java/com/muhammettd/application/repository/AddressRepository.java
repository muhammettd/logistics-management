package com.muhammettd.application.repository;

import com.muhammettd.application.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

    List<Address> findByCustomerId(String customerId);

}
