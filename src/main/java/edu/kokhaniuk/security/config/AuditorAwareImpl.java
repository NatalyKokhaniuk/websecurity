package edu.kokhaniuk.security.config;

/*
  @author nataly
  @project security
  @class AuditorAwareImpl
  @version 1.0.0
  @since 01/15/2026 - 08.17
*/

import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
