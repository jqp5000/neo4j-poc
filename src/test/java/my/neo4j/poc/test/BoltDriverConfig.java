package my.neo4j.poc.test;

import my.neo4j.poc.AppConfig;
import org.neo4j.ogm.config.Configuration;
import org.springframework.context.annotation.Bean;

public class BoltDriverConfig extends AppConfig {

    @Override
    @Bean
    public Configuration getConfiguration() {
        Configuration config = new Configuration();

        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setURI("bolt://localhost")
                .setEncryptionLevel("NONE");

        return config;
    }

}
