package com.royaltheorem.Designer.Service;

import com.netflix.discovery.converters.Auto;
import com.royaltheorem.Designer.Designer;
import com.royaltheorem.Designer.DesignerRepository.DesignerRepository;
import com.royaltheorem.Designer.Designs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
public class DesignerServiceImplementation implements DesignerService{

    @Autowired
    DesignerRepository designerRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;

    @Override
    @CacheEvict(value = "getAllDesigners",allEntries = true)
    public String addDesigner(Designer designer) {
        designerRepository.save(designer);
        return designer.getName()+" saved successfully";
    }

    @Override
    public Optional<Designer> getDesigner(int id) {

        List<ServiceInstance> instances = discoveryClient.getInstances("DESIGNSERVICE");
        String url=instances.get(0).getUri().toString();
        List<Designs> designs=restTemplate.getForObject(url+"/getAllDesignsById/"+id,List.class);
        Optional<Designer> designer=designerRepository.findById(id);

        designer.stream().toList().get(0).setDesigns(designs);
        return designer;
    }

    @Override
    @Cacheable("getAllDesigners")
    public List<Designer> getAllDesigners() {
        System.out.println("getAll called");
        return designerRepository.findAll();
    }

    @Override
    public String updateRating(int id, int rating) {
        Optional<Designer> designer=designerRepository.findById(id);
        if(designer.isPresent())
        {
            Designer updatedDesigner=designer.get();
            updatedDesigner.setRating(rating);
            designerRepository.save(updatedDesigner);
            return "updated successfully...";
        }

        return "sorry no designer with id "+id;
    }

    @Override
    public int getRating(int id) {
        return designerRepository.getRating(id);
    }

    @Override
    public String deleteDesigner(int id) {
        designerRepository.deleteById(id);
        return id+" deleted successfully";
    }

    @Override
    public String deleteAllDesigners() {
        designerRepository.deleteAll();
        return "all designers deleted succesfully";
    }
}
