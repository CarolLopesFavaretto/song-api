package song.api.com.br.song.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import song.api.com.br.song.dao.mapper.FavoriteMapper;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.domain.entity.FavoriteSong;
import song.api.com.br.song.repository.FavoriteRepository;
import song.api.com.br.song.repository.UserRepository;
import song.api.com.br.song.service.FavoriteSongsService;

import java.util.List;
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
    public ResponseEntity<SongsResponse> save(SongsRequest request, String token) {
        request.setUserId(validateTokenAndGetUserId(token));
        FavoriteSong song = mapper.toModel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(repository.save(song)));
    }

    @Override
    public List<SongsResponse> findBySong(String songFavorite, String token) {
        Long userId = validateTokenAndGetUserId(token);
        List<FavoriteSong> favoriteSongs = repository.findBySongFavorite(songFavorite, userId);
        return mapper.toModelList(favoriteSongs);
    }

    @Override
    public ResponseEntity<SongsResponse> updateSong(Long id, SongsRequest request, String token) {
        Optional<FavoriteSong> songOptional = repository.findById(id);

        if (songOptional.isPresent()) {
            FavoriteSong song = songOptional.get();
            song.setSongFavorite(request.getSongFavorite());
            song.setArtist(request.getArtist());
            song.setAlbum(request.getAlbum());
            request.setUserId(validateTokenAndGetUserId(token));
            return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(repository.save(song)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public void deleteSong(Long id, String token) {
        Long userId = validateTokenAndGetUserId(token);
        Optional<FavoriteSong> songOptional = repository.findById(id);
        if (songOptional.isPresent()) {
            FavoriteSong song = songOptional.get();
            song.setUserId(userId);
            repository.deleteById(id);
        }
    }


    private Long validateTokenAndGetUserId(String token) {
        String newToken = token.substring(7, token.length());
        Claims claims2 = null;
        try {
            claims2 = Jwts.parser()
                    .setSigningKey("413F4428472B4B6250655368566D5970337336763979244226452948404D6351")
                    .parseClaimsJws(newToken)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Long.valueOf(claims2.get("id").toString());
    }


}