package in.inst.photoalbummanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.inst.photoalbummanager.beans.Photo;
import in.inst.photoalbummanager.repo.AlbumRepository;
import in.inst.photoalbummanager.repo.PhotoRepository;

@RestController
@RequestMapping("/{albumId}/photos")
public class PhotoContoller {
	
	private PhotoRepository photoRepo;
	private AlbumRepository albumRepo;
	@Autowired
	public PhotoContoller(PhotoRepository photoRepo, AlbumRepository albumRepo) {
		
		this.photoRepo = photoRepo;
		this.albumRepo = albumRepo;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Photo> read(@PathVariable long albumId) {
		System.out.println("--> "+this.albumRepo.findById(albumId).getPhotos().size());
		System.out.println("--> "+this.photoRepo.findByAlbum(this.albumRepo.findOne(albumId)));
		return this.photoRepo.findByAlbum(this.albumRepo.findOne(albumId));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> udpate() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete() {
		return null;
	}
}
