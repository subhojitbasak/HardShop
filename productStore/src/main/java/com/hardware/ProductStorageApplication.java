package com.hardware;

//import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEncryptableProperties
public class ProductStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductStorageApplication.class, args);
	}

}
