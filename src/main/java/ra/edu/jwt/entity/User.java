package ra.edu.jwt.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.jwt.common.enums.RoleName;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private RoleName role; // ví dụ: ROLE_USER, ROLE_ADMIN

    private String email;
    private String fullName;
    private boolean enabled = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    // getters và setters
}
