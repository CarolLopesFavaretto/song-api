package song.api.com.br.song.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import song.api.com.br.song.dao.request.SongsRequest;
import song.api.com.br.song.dao.response.SongsResponse;
import song.api.com.br.song.domain.entity.FavoriteSongs;
import song.api.com.br.song.domain.entity.Users;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface FavoriteMapper {

    FavoriteMapper INSTANCE = Mappers.getMapper(FavoriteMapper.class);

    SongsResponse toResponse(FavoriteSongs songs);

    FavoriteSongs toModel(SongsRequest request);
}
