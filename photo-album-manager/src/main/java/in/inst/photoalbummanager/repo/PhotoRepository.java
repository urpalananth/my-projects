package in.inst.photoalbummanager.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.inst.photoalbummanager.beans.Photo;

public interface PhotoRepository  extends JpaRepository<Photo, Serializable>{

}
