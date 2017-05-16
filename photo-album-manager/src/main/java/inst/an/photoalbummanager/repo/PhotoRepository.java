package inst.an.photoalbummanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inst.an.photoalbummanager.beans.Album;
import inst.an.photoalbummanager.beans.Photo;

public interface PhotoRepository  extends JpaRepository<Photo, Long>{

	List<Photo> findByAlbumId(long albumId);

	List<Photo> findByAlbum(Album album);

}
