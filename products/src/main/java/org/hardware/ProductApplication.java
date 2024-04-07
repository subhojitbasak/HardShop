package org.hardware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ProductApplication {

    public static void main(String[] args) {
        log.info("hello");
        SpringApplication.run(ProductApplication.class, args);

    }
}

