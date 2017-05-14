package in.inst.photoalbummanager.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.inst.photoalbummanager.beans.Album;

public interface AlbumRepository extends JpaRepository<Album, Serializable>{

}
