package dev.spring.p10walletapp.service;

import dev.spring.p10walletapp.dto.LoginDto;
import dev.spring.p10walletapp.model.Login;
import dev.spring.p10walletapp.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void save(LoginDto loginDto) {
        mapToDto(this.loginRepository.save(
                new Login(
                    loginDto.getUsername(),
                    loginDto.getPassword()
                )
        ));
    }

    public static LoginDto mapToDto(Login login) {
        if (login != null) {
            return new LoginDto(
                    login.getUsername(),
                    login.getPassword()
            );
        }
        return null;
    }
}
