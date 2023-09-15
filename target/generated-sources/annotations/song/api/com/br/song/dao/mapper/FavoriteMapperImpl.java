package song.api.com.br.song.dao.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.domain.entity.FavoriteSongs;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T18:09:20-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class FavoriteMapperImpl implements FavoriteMapper {

    @Override
    public SongsResponse toResponse(FavoriteSongs songs) {
        if ( songs == null ) {
            return null;
        }

        SongsResponse.SongsResponseBuilder songsResponse = SongsResponse.builder();

        songsResponse.id( songs.getId() );
        songsResponse.songFavorite( songs.getSongFavorite() );
        songsResponse.artist( songs.getArtist() );
        songsResponse.album( songs.getAlbum() );
        songsResponse.userId( songs.getUserId() );

        return songsResponse.build();
    }

    @Override
    public FavoriteSongs toModel(SongsRequest request) {
        if ( request == null ) {
            return null;
        }

        FavoriteSongs favoriteSongs = new FavoriteSongs();

        favoriteSongs.setSongFavorite( request.getSongFavorite() );
        favoriteSongs.setArtist( request.getArtist() );
        favoriteSongs.setAlbum( request.getAlbum() );
        favoriteSongs.setUserId( request.getUserId() );

        return favoriteSongs;
    }
}
