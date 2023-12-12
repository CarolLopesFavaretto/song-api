package song.api.com.br.song.service;

import org.springframework.http.ResponseEntity;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;

import java.util.List;

public interface FavoriteSongsService {
    ResponseEntity<SongsResponse> save(SongsRequest request, String token);

    List<SongsResponse> findBySong(String songFavorite, String token);
}
