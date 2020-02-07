package com.justas.project.server.bean;

import com.justas.project.library.services.PlaygroundService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PlaygroundService playgroundService() {
        return new PlaygroundService();
    }
}
