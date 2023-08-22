package com.royaltheorem.Designer.DesignerRepository;

import com.royaltheorem.Designer.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface DesignerRepository extends JpaRepository<Designer,Integer> {
    //int updateRating(@Param("rating") int rating,@Param("id") int id);

    @Query(value = "select rating from designer where id=:id",nativeQuery = true)
    int getRating(@Param("id") int id);
}
