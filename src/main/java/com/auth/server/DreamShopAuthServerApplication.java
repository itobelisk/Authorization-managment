package com.auth.server;

import com.auth.server.config.AppProperties;
import com.auth.server.services.positions.impl.PositionsServiceImpl;
import com.auth.server.services.positonsCategory.impl.PositionCategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableEurekaClient
@RequiredArgsConstructor

@EnableJpaRepositories
public class DreamShopAuthServerApplication extends SpringBootServletInitializer implements CommandLineRunner {
    private final PositionCategoryServiceImpl positionsCategoryServiceImpl;
    private final PositionsServiceImpl positionsService;

    public static void main(String[] args) {
        SpringApplication.run(DreamShopAuthServerApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DreamShopAuthServerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        positionsCategoryServiceImpl.fillData();
        positionsService.fillAdministrationData();
        positionsService.fillFinancialData();
        positionsService.fillManagementData();
        positionsService.fillStuffData();;
    }
}
