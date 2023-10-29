package olegivanov;

import olegivanov.dtos.RegisterRequest;
import olegivanov.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static olegivanov.entities.Role.ADMIN;
import static olegivanov.entities.Role.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MyCloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCloudStorageApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Oleg")
					.lastname("Ivanov")
					.email("oleg@gmail.com")
					.password("oleg")
					.role(ADMIN)
					.build();
			System.out.println( "Token for " + admin.getEmail() + " : " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("User")
					.lastname("User")
					.email("user@mail.ru")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("User token: " + service.register(manager).getAccessToken());

		};
	}


}
