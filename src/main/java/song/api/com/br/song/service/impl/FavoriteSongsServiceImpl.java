package song.api.com.br.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import song.api.com.br.song.dao.mapper.FavoriteMapper;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.dao.response.UserResponse;
import song.api.com.br.song.domain.entity.FavoriteSongs;
import song.api.com.br.song.domain.entity.Users;
import song.api.com.br.song.repository.FavoriteRepository;
import song.api.com.br.song.repository.UserRepository;
import song.api.com.br.song.service.FavoriteSongsService;

import java.util.Optional;

@Service
public class FavoriteSongsServiceImpl implements FavoriteSongsService {

    @Autowired
    private FavoriteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteMapper mapper;

    @Override
    public ResponseEntity<SongsResponse> saved(SongsRequest request) {
        try {
            Optional<Users> users = userRepository.findById(request.getUserId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        FavoriteSongs songs = mapper.toModel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(repository.save(songs)));
    }

}