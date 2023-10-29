package olegivanov;

import olegivanov.dtos.AuthenticationResponse;
import olegivanov.dtos.RegisterRequest;
import olegivanov.entities.Role;
import olegivanov.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@SpringBootTest
class MyCloudStorageApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test_startApplication() {
		// Arrange
		String[] args = new String[] {};

		// Act
		assertDoesNotThrow(() -> MyCloudStorageApplication.main(args));

		// Assert
		// No exceptions are thrown
	}

	@Test
	public void test_registerAdminUser() throws Exception {
		// Arrange
		AuthenticationService authenticationService = mock(AuthenticationService.class);
		CommandLineRunner commandLineRunner = new MyCloudStorageApplication().commandLineRunner(authenticationService);
		RegisterRequest admin = RegisterRequest.builder()
				.firstname("Oleg")
				.lastname("Ivanov")
				.email("oleg@gmail.com")
				.password("oleg")
				.role(Role.ADMIN)
				.build();
		AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
				.accessToken("admin_token")
				.build();
		when(authenticationService.register(admin)).thenReturn(expectedResponse);

		// Act
		commandLineRunner.run();

		// Assert
		verify(authenticationService).register(admin);
	}
}
