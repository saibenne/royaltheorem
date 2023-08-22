package com.royaltheorem.Designs.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.royaltheorem.Designs.Design;
import com.royaltheorem.Designs.Repository.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public class DesignServiceImplementation implements DesignService{

    @Autowired
    DesignRepository designRepository;
    @Override
    public List<Design> getAllDesigns() {
        return designRepository.findAll();
    }

    @Override

    public List<Design> getAllDesignsWithId(int id) {
        return designRepository.findByDesignerId(id);
    }

    @Override
    public String addDesign(Design design) {
        designRepository.save(design);
        return design.getDesignName()+" added successfully...";
    }

    @Override
    public String updateRating(int id,int rating) {
        Optional<Design> design=designRepository.findById(id);
        if(design.isPresent())
        {
            Design updatedDesign =design.get();
            updatedDesign.setRating(rating);
            designRepository.save(updatedDesign);
            return "updated Successfully...";
        }
        return "sorry, no design with id ."+id;
    }

    @Override
    public Optional<Design> getDesign(int id) {
        return designRepository.findById(id);
    }
}
