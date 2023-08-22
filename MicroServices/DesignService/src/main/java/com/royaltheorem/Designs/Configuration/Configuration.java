package com.royaltheorem.Designs.Configuration;

import com.royaltheorem.Designs.Service.DesignService;
import com.royaltheorem.Designs.Service.DesignServiceImplementation;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public DesignService designService()
    {
        return new DesignServiceImplementation();
    }
}
