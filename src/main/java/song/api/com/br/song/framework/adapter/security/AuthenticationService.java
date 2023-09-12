package song.api.com.br.song.framework.adapter.security;

import song.api.com.br.song.framework.adapter.request.SignInRequest;
import song.api.com.br.song.framework.adapter.request.SignUpRequest;
import song.api.com.br.song.framework.adapter.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
