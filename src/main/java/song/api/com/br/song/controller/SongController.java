package song.api.com.br.song.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.service.FavoriteSongsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/song")
@RequiredArgsConstructor
public class SongController {

    @Autowired
    private FavoriteSongsService service;

    @PostMapping("/favorite")
    public ResponseEntity<SongsResponse> saveSong(@RequestBody SongsRequest request,
                                                  @RequestHeader("Authorization") String token) {
        return service.save(request, token);
    }

    @GetMapping("/favorite")
    public List<SongsResponse> findBySong(@RequestParam String songFavorite,
                                          @RequestHeader(value = "Authorization") String token) {
        return service.findBySong(songFavorite, token);
    }
}
