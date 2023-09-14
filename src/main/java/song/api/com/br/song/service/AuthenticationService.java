package song.api.com.br.song.service;

import song.api.com.br.song.dao.request.SignUpRequest;
import song.api.com.br.song.dao.request.SigninRequest;
import song.api.com.br.song.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
