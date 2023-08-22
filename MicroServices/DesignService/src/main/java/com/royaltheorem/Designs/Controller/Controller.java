package com.royaltheorem.Designs.Controller;

import com.royaltheorem.Designs.Design;
import com.royaltheorem.Designs.Service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    DesignService designService;
    @PostMapping("/addDesign")
    public String addDesign(@RequestBody Design design){return designService.addDesign(design);}

    @GetMapping("/getAllDesigns")
    public List<Design> getAllDesigns(){return designService.getAllDesigns();}

    @GetMapping("/getAllDesignsById/{id}")
    public List<Design> getAllDesignsById(@PathVariable("id") int id){return designService.getAllDesignsWithId(id);}

    @GetMapping("getDesignById/{id}")
    public Optional<Design> getDesignById(@PathVariable("id") int id){return designService.getDesign(id);}

    @GetMapping("/updateRating/{id}/{rating}")
    public String updateRating(@PathVariable("id") int id,@PathVariable("rating") int rating)
    {
        return designService.updateRating(id,rating);
    }
}
