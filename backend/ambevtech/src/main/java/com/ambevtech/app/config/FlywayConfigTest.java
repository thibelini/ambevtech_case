package com.ambevtech.app.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class FlywayConfigTest {

    @Autowired
    private ApplicationYMLConfig ymlConfig;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(ymlConfig.getSpring().getDatasource().get("url"),
                        ymlConfig.getSpring().getDatasource().get("username"),
                        ymlConfig.getSpring().getDatasource().get("password"))
                .baselineDescription("Criação do banco de dados da api do Card").baselineOnMigrate(true)
                .schemas("ambevtech_teste").outOfOrder(true).locations("classpath:/db/migration", "classpath:/db/test")
                .load();

        flyway.clean();

        return flyway;
    }

}

