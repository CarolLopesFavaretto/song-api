package song.api.com.br.song.service;

import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;

public interface FavoriteSongsService {

    SongsResponse saved (SongsRequest request);
}
