INSERT INTO users (username, password, role, email, full_name, enabled, created_at)
VALUES
    ('admin01', '$2a$10$p.oD0jT1i/RyclnwsIePk.9zLPEAR1u7YCs17NXndZDZ6kpZFuhvS', 'ROLE_ADMIN', 'admin01@example.com', 'Nguyễn Văn Admin', true, NOW()),
    ('user01', '$2a$10$p.oD0jT1i/RyclnwsIePk.9zLPEAR1u7YCs17NXndZDZ6kpZFuhvS', 'ROLE_USER', 'user01@example.com', 'Trần Thị User', true, NOW());
