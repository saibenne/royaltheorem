package com.royaltheorem.Designer.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.royaltheorem.Designer.Designer;
import com.royaltheorem.Designer.Service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    DesignerService designerService;
    @PostMapping("/addDesigner")
    public String addDesigner(@RequestBody Designer designer){return designerService.addDesigner(designer);}

    @GetMapping("/getDesigner/{id}")
    public Optional<Designer> getDesigner(@PathVariable("id") int id){return designerService.getDesigner(id);}


    @GetMapping("/getAllDesigners")
    public List<Designer> getAllDesigners(){
        //System.out.println(" method run");
        return designerService.getAllDesigners();}

    @PutMapping("/updateRating/{id}/{rating}")
    public String updateRating(@PathVariable("id") int id, @PathVariable("rating") int rating){
        try {
            System.out.println("method call");
            return ""+ designerService.updateRating(id, rating);
        }
        catch(Exception e)
        {
            return e+" ";
        }
    }
    @GetMapping("/getRating/{id}")
    public String getRating(@PathVariable("id") int id)
    {
        return designerService.getRating(id)+" ";
    }
    @DeleteMapping("/deleteDesigner/{id}")
    public String deleteDesigner(@PathVariable("id") int id){return designerService.deleteDesigner(id);}

    @DeleteMapping("/deleteAllDesigners")
    public String deleteAllDesigners(){return designerService.deleteAllDesigners();}



}
