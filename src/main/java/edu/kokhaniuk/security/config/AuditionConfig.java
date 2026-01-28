package edu.kokhaniuk.security.config;

/*
  @author nataly
  @project security
  @class AuditionConfig
  @version 1.0.0
  @since 01/15/2026 - 08.17
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@Configuration
public class AuditionConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

}