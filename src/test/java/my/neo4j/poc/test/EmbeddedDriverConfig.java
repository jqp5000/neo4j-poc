package my.neo4j.poc.test;

import my.neo4j.poc.AppConfig;
import org.neo4j.ogm.config.Configuration;
import org.springframework.context.annotation.Bean;

public class EmbeddedDriverConfig extends AppConfig {

    @Bean
    public Configuration getConfiguration() {
        Configuration config = new Configuration();

        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");

        return config;
    }

}
