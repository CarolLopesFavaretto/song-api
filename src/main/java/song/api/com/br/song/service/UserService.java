package song.api.com.br.song.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import song.api.com.br.song.domain.entity.Users;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();

    Optional<Users> findById(Long userId);
}
