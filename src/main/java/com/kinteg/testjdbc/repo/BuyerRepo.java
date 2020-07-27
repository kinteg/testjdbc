package com.kinteg.testjdbc.repo;

import com.kinteg.testjdbc.model.Buyer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepo {

    Iterable<Buyer> findAll();

    Optional<Buyer> findById(Long id);

    Optional<Buyer> save(Buyer buyer);

}
