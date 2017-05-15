package in.inst.photoalbummanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.inst.photoalbummanager.beans.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{

	List<Album> findByUserId(long userId);

	Album findById(long albumId);
}
