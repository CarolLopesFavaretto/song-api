package song.api.com.br.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.repository.FavoriteRepository;
import song.api.com.br.song.service.FavoriteSongsService;

@Service
public class FavoriteSongsServiceImpl implements FavoriteSongsService {

    @Autowired
    private FavoriteRepository repository;

    @Override
    public SongsResponse saved(SongsRequest request) {
        return null;
    }
}
