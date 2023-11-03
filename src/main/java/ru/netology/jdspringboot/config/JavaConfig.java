package ru.netology.jdspringboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.jdspringboot.profile.DevProfile;
import ru.netology.jdspringboot.profile.ProductionProfile;
import ru.netology.jdspringboot.profile.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile(){
        return new DevProfile();
    }
    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "false")
    public SystemProfile productionProfile(){
        return new ProductionProfile();
    }
}
