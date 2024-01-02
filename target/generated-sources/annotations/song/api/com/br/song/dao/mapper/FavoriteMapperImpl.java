package song.api.com.br.song.dao.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.domain.entity.FavoriteSong;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T14:55:40-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class FavoriteMapperImpl implements FavoriteMapper {

    @Override
    public SongsResponse toResponse(FavoriteSong songs) {
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
    public FavoriteSong toModel(SongsRequest request) {
        if ( request == null ) {
            return null;
        }

        FavoriteSong favoriteSong = new FavoriteSong();

        favoriteSong.setSongFavorite( request.getSongFavorite() );
        favoriteSong.setArtist( request.getArtist() );
        favoriteSong.setAlbum( request.getAlbum() );
        favoriteSong.setUserId( request.getUserId() );

        return favoriteSong;
    }

    @Override
    public List<SongsResponse> toModelList(List<FavoriteSong> songs) {
        if ( songs == null ) {
            return null;
        }

        List<SongsResponse> list = new ArrayList<SongsResponse>( songs.size() );
        for ( FavoriteSong favoriteSong : songs ) {
            list.add( toResponse( favoriteSong ) );
        }

        return list;
    }
}
