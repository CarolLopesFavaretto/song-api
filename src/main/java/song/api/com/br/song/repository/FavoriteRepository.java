package song.api.com.br.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import song.api.com.br.song.domain.entity.FavoriteSong;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteSong, Long> {
}
