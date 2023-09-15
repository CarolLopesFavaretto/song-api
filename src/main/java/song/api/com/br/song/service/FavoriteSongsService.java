package song.api.com.br.song.service;

import org.springframework.http.ResponseEntity;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.domain.entity.FavoriteSongs;

public interface FavoriteSongsService {

    ResponseEntity<SongsResponse> saved (SongsRequest request);
}
