package com.royaltheorem.Designs.Repository;

import com.royaltheorem.Designs.Design;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface DesignRepository extends MongoRepository<Design,Integer> {
    @Query("{'designerId':?0}")
    List<Design> findByDesignerId(int id);
}
