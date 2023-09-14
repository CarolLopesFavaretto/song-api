package song.api.com.br.song.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.service.FavoriteSongsService;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {

    @Autowired
    private FavoriteSongsService service;

    @PostMapping("/favorite")
    public ResponseEntity<SongsResponse> savedSongs(@RequestBody SongsRequest request) {
        return ResponseEntity.ok(service.saved(request));
    }
}
