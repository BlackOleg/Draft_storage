package olegivanov.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
public class JwtRequest {
    @NotBlank(message = "login must not be null")
    private String username;

    @NotBlank(message = "password must not be null")
    @Size(min = 2,max = 20, message = "Password should be between 2 and 20 characters")
    private String password;
}
