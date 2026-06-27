package com.projeto.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")//define o caminho da url
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {

        packages(//define os pacotes (esse e o resource)
              "com.projeto.resource",
              "com.projeto.config"
              );
     // habilita o Jackson que transforma JSON em classe e vice e versa
        register(JacksonFeature.class); 
    }
}