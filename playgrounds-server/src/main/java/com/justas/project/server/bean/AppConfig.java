package com.justas.project.server.bean;

import com.justas.project.library.services.HistoryService;
import com.justas.project.library.services.PlaygroundService;
import com.justas.project.library.services.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppConfig {
    @Bean
    public PlaygroundService playgroundService() {
        return new PlaygroundService();
    }

    @Bean
    public TicketService getTicketService() {
        return new TicketService();
    }

    @Bean
    public HistoryService getHistoryService() {
        return new HistoryService();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
