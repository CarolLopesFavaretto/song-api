package song.api.com.br.song.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongsRequest {

    private String songFavorite;
    private String artist;
    private String album;
    private Long userId;

}
