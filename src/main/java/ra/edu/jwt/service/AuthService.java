package ra.edu.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.edu.jwt.dto.request.LoginRequest;
import ra.edu.jwt.dto.request.RegisterRequest;
import ra.edu.jwt.entity.User;
import ra.edu.jwt.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
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

    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            // ở đây bạn có thể sinh JWT token thay vì trả plain text
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }
}
