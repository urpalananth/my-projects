package in.inst.photoalbummanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.inst.photoalbummanager.beans.Album;
import in.inst.photoalbummanager.beans.Photo;

public interface PhotoRepository  extends JpaRepository<Photo, Long>{

	List<Photo> findByAlbumId(long albumId);

	List<Photo> findByAlbum(Album album);

}
