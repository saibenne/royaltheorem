package com.royaltheorem.Designer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Designs {
    @JsonProperty("designId")
    int designId;
    @JsonProperty("designerId")
    int designerId;
    @JsonProperty("designerName")
    String designerName;
    @JsonProperty("designName")
    String designName;
    @JsonProperty("url")
    String url;
    @JsonProperty("rating")
    int rating;
}
