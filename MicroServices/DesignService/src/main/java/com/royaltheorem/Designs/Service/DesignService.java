package com.royaltheorem.Designs.Service;

import com.royaltheorem.Designs.Design;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public interface DesignService {
    List<Design> getAllDesigns();
    List<Design> getAllDesignsWithId(int id);
    String addDesign(Design design);
    String updateRating(int id,int rating);
    Optional<Design> getDesign(int id);

}
