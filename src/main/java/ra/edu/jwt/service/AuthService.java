package ra.edu.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.jwt.dto.request.LoginRequest;
import ra.edu.jwt.dto.request.RegisterRequest;
import ra.edu.jwt.dto.response.JwtResponse;
import ra.edu.jwt.entity.User;
import ra.edu.jwt.repository.UserRepository;
import ra.edu.jwt.security.jwt.JwtUtils;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Value("${jwt.expired}")
    private long expired;

    public String register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists!";
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setRole(request.getRole());
        userRepository.save(user);
        return "User registered successfully!";
    }

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            // ở đây bạn có thể sinh JWT token thay vì trả plain text
            String accessToken = jwtUtils.generateAccessToken(request.getUsername());
            return JwtResponse.builder()
                    .accessToken(accessToken)
                    .expiredAt(new Date(new Date().getTime()+expired))
                    .build();
        } else {
            throw new RuntimeException("Username or password incorrect !!!");
        }
    }
}
