package dev.spring.p10walletapp.repository;

import dev.spring.p10walletapp.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);
}
