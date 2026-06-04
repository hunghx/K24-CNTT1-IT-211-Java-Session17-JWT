package ra.edu.jwt.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private final String type = "Bearer Token";
    private Date expiredAt;
}
