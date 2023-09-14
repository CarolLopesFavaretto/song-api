package song.api.com.br.song.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/favorite-songs")
@RequiredArgsConstructor
public class SongController {

//    @PostMapping
//    public ResponseEntity<String> sayHello() {
//        return ResponseEntity.ok("Here is your resource");
//    }
}
