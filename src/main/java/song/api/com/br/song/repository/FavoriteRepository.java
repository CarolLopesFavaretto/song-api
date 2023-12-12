package song.api.com.br.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import song.api.com.br.song.domain.entity.FavoriteSong;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteSong, Long> {

    @Query("select f from FavoriteSong f where f.songFavorite = :songFavorite and f.userId = :userId")
    List<FavoriteSong> findBySongFavorite(@Param("songFavorite") String songFavorite, @Param("userId") Long userId);
}
