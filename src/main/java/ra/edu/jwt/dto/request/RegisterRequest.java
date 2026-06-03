package ra.edu.jwt.dto.request;

import lombok.Data;
import ra.edu.jwt.common.enums.RoleName;
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private RoleName role; // enum ROLE_USER, ROLE_ADMIN
    // getters & setters
}