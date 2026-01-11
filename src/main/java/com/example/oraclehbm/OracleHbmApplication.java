package com.example.oraclehbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class OracleHbmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleHbmApplication.class, args);
    }
}
