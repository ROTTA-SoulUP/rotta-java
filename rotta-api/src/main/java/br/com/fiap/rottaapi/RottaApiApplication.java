package br.com.fiap.rottaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RottaApiApplication {

    public static void main(String[] args) {
        System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
        System.out.println("DB_PASSWORD definida: " + (System.getenv("DB_PASSWORD") != null));
        SpringApplication.run(RottaApiApplication.class, args);
    }

}
