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

    private Long favoriteId;
    private String songName;
    private String artist;
    private String album;

}
