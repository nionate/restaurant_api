package cl.transbank.restaurant_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApiApplication {

	public static void main(String[] args) {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");

		SpringApplication.run(RestaurantApiApplication.class, args);
	}

}
