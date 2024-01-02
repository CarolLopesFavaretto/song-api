package song.api.com.br.song.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import song.api.com.br.song.config.PostgreSQLContainerTest;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.domain.Enum.Role;
import song.api.com.br.song.domain.entity.User;
import song.api.com.br.song.repository.UserRepository;
import song.api.com.br.song.service.FavoriteSongsService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SongControllerTest extends PostgreSQLContainerTest {

    //    TODO - não deixar valor token exposto
    public static final String Authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsInN1YiI6ImNhcm9saW5lbG9wZ" +
            "XMyQGdtYWlsLmNvbSIsImlhdCI6MTcwNDIxNzk2NywiZXhwIjoxNzA0MzYxOTY3fQ.cN4rpuWlm1mSKrcLNxzkIjVLL8YA9pozeD0vLSChDso";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FavoriteSongsService service;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldCreateSong() throws Exception {

        //        TODO - deixar codigo mais dinamico

        // salvando usuario na base
        User userSave = userRepository.save(User.builder()
                .id(1L)
                .email("carolinelopes2@gmail.com")
                .password(passwordEncoder.encode("1234567"))
                .roles(Role.USER)
                .build());

        // request
        SongsRequest song = SongsRequest.builder()
                .songFavorite("Love")
                .album("Gilsons")
                .artist("Gilsons")
                .userId(userSave.getId())
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/song/favorite")
                        .header("Authorization", Authorization)
                        .content(objectMapper.writeValueAsString(song))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    SongsResponse songsResponse = objectMapper.readValue(contentAsString, SongsResponse.class);
                    assertThat(songsResponse.getId()).isNotNull();
                    assertThat(songsResponse.getSongFavorite()).isEqualTo(song.getSongFavorite());
                });

    }

    @Test
    void shouldUpdateSong() throws Exception {

//        TODO - deixar codigo mais dinamico

        // salvando usuario na base
        User userSave = userRepository.save(User.builder()
                .id(1L)
                .email("carolinelopes2@gmail.com")
                .password(passwordEncoder.encode("1234567"))
                .roles(Role.USER)
                .build());


        // Salavando song na base
        SongsRequest song = SongsRequest.builder()
                .songFavorite("Love")
                .album("Gilsons")
                .artist("Gilsons")
                .userId(userSave.getId())
                .build();

        service.save(song, Authorization);

        // request Update
        SongsRequest songUpdate = SongsRequest.builder()
                .songFavorite("Varias queixas")
                .album("Gilsons")
                .artist("Gilsons")
                .userId(userSave.getId())
                .build();


        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/song/favorite/{id}", 1L)
                        .header("Authorization", Authorization)
                        .content(objectMapper.writeValueAsString(songUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    SongsResponse songsResponse = objectMapper.readValue(contentAsString, SongsResponse.class);
                    assertThat(songsResponse.getId()).isNotNull();
                    assertThat(songsResponse.getSongFavorite()).isEqualTo(songUpdate.getSongFavorite());
                    assertThat(songsResponse.getAlbum()).isEqualTo(songUpdate.getAlbum());
                    assertThat(songsResponse.getArtist()).isEqualTo(songUpdate.getArtist());
                });

    }

    @Test
    void shouldListBySong() throws Exception {

//        TODO - deixar codigo mais dinamico

        // salvando usuario na base
        User userSave = userRepository.save(User.builder()
                .id(1L)
                .email("carolinelopes2@gmail.com")
                .password(passwordEncoder.encode("1234567"))
                .roles(Role.USER)
                .build());


        // Salvando song na base
        SongsRequest song = SongsRequest.builder()
                .songFavorite("Love")
                .album("Gilsons")
                .artist("Gilsons")
                .userId(userSave.getId())
                .build();

        service.save(song, Authorization);


        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/song/favorite")
                        .param("songFavorite", song.getSongFavorite())
                        .header("Authorization", Authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();

                    List<SongsResponse> resp = objectMapper.readValue(contentAsString, new TypeReference<List<SongsResponse>>() {
                    });
                    assertThat(resp.size()).isEqualTo(1);

                    for (SongsResponse item : resp) {
                        assertThat(item.getSongFavorite()).isEqualTo(song.getSongFavorite());
                        assertThat(item.getAlbum()).isEqualTo(song.getAlbum());
                        assertThat(item.getArtist()).isEqualTo(song.getArtist());

                    }
                });
    }

}