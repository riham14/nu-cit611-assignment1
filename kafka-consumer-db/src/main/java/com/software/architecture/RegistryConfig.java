package com.software.architecture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;

@Configuration
public class RegistryConfig {
    @Value("${spring.application.name}")
    String appName;

    @Bean
    MeterRegistryCustomizer<PrometheusMeterRegistry> configureMetricsRegistry() {
        return registry -> registry.config().commonTags("appName", appName);
    }
}
