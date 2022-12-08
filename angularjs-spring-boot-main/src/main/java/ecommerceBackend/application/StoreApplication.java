package ecommerceBackend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ecommerceBackend")
@EnableJpaRepositories(basePackages = "ecommerceBackend.repository")
@EntityScan("ecommerceBackend.entity")
public class StoreApplication {

//	public StoreApplication() {
//		//...
//	}
	public static void main(String... args) {
      SpringApplication.run(StoreApplication.class, args);
  }

}
