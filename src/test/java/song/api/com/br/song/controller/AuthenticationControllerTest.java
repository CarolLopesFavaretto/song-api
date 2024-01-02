package song.api.com.br.song.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import song.api.com.br.song.config.PostgreSQLContainerTest;
import song.api.com.br.song.dao.request.SignUpRequest;
import song.api.com.br.song.dao.request.SigninRequest;
import song.api.com.br.song.dao.response.JwtAuthenticationResponse;
import song.api.com.br.song.domain.Enum.Role;
import song.api.com.br.song.domain.entity.User;
import song.api.com.br.song.repository.UserRepository;
import song.api.com.br.song.service.AuthenticationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthenticationService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    EasyRandom easy = new EasyRandom();

    @Test
    void shouldSignUp() throws Exception {

        SignUpRequest request = easy.nextObject(SignUpRequest.class);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/signup")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    JwtAuthenticationResponse response = objectMapper.readValue(contentAsString, JwtAuthenticationResponse.class);
                    assertThat(response.getToken()).isNotNull();
                });

    }

    @Test
    void shouldSignIn() throws Exception {
        SigninRequest request = easy.nextObject(SigninRequest.class);
        request.setPassword("teste");

        userRepository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.USER)
                .build());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/signin")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    JwtAuthenticationResponse response = objectMapper.readValue(contentAsString, JwtAuthenticationResponse.class);
                    assertThat(response.getToken()).isNotNull();
                });


    }
}
