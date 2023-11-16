package com.spines.fleetmanagementsoftware.configurations;

import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.Trip;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        config.exposeIdsFor(Vehicle.class);
        config.exposeIdsFor(Trip.class);
        config.exposeIdsFor(Driver.class);
        config.exposeIdsFor(Maintenance.class);

        cors.addMapping(config.getBasePath() + "/**").allowedMethods("GET","POST","DELETE","PUT").allowedOrigins("http://localhost:3000/");
    }
}
