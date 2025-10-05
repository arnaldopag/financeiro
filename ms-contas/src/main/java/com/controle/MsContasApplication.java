package com.controle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.controle.contas.repository.jpa")
@EnableMongoRepositories(basePackages = "com.controle.contas.repository.mongo")
public class MsContasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsContasApplication.class, args);
    }
}