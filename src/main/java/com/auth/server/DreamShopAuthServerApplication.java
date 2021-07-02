package com.auth.server;

import com.auth.server.config.AppProperties;
import com.auth.server.entity.position.response.PositionResponse;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.mapper.PositionsMapper;
import com.auth.server.repository.PositionsRepository;
import com.auth.server.services.positions.impl.CommandPositionServiceImplementation;
import com.auth.server.services.positonsCategory.impl.PositionCategoryServiceImpl;
import com.auth.server.services.role.CommandRoleService;
import com.auth.server.services.user.impl.CommandUserServiceImplementation;
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
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableEurekaClient
@RequiredArgsConstructor

@EnableJpaRepositories
public class DreamShopAuthServerApplication extends SpringBootServletInitializer implements CommandLineRunner {
    private final PositionCategoryServiceImpl positionsCategoryServiceImpl;
    private final CommandPositionServiceImplementation positionsService;
    private final CommandUserServiceImplementation commandUserImplementation;
    private final CommandRoleService roleControllerService;
    private final PositionsMapper positionsMapper;
    private final PositionsRepository positionsRepository;

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

        List<PositionResponse> allPositions = positionsMapper.toResponse(positionsRepository.findAll());
        if (allPositions.isEmpty()) {
            roleControllerService.createAdminRole( RoleRequest.builder().name("admin").build());
            positionsCategoryServiceImpl.fillData();
            positionsService.fillAdministrationData();
            positionsService.fillFinancialData();
            positionsService.fillManagementData();
            positionsService.fillStuffData();
            commandUserImplementation.addAdmin();
        }
    }
}
