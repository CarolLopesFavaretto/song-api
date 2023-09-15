package song.api.com.br.song.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongsResponse {

    private Long id;
    private String songFavorite;
    private String artist;
    private String album;
    private Long userId;

}
