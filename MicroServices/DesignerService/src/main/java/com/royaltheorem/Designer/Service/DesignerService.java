package com.royaltheorem.Designer.Service;

import com.royaltheorem.Designer.Designer;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public interface DesignerService {
    String addDesigner(Designer designer);
    Optional<Designer> getDesigner(int id);
    List<Designer> getAllDesigners();
    String updateRating(int id,int rating);
    int getRating(int id);
    String deleteDesigner(int id);
    String deleteAllDesigners();


}
