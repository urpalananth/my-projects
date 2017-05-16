package inst.an.photoalbummanager.controller;

import java.net.URI;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import inst.an.photoalbummanager.beans.Album;
import inst.an.photoalbummanager.beans.Photo;
import inst.an.photoalbummanager.repo.AlbumRepository;
import inst.an.photoalbummanager.repo.PhotoRepository;
import inst.an.photoalbummanager.repo.UserRepository;

@RestController
@RequestMapping("/{albumId}/photos")
public class PhotoContoller {
	private static final Logger LOGGER = Logger.getLogger("PAMUserDetailsService");
	private PhotoRepository photoRepo;
	private AlbumRepository albumRepo;
	private UserRepository userRepo;
	@Autowired
	public PhotoContoller(PhotoRepository photoRepo, AlbumRepository albumRepo, UserRepository userRepo) {
		
		this.photoRepo = photoRepo;
		this.albumRepo = albumRepo;
		this.userRepo = userRepo;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> read(@PathVariable long albumId) {
		Album album = this.albumRepo.findOne(albumId);
		if(album == null)
			return ResponseEntity.badRequest().body("Album not found!");
		if(!validate(album))
			return ResponseEntity.status(403).body("Forbidden access to this album!");
		return ResponseEntity.ok(this.photoRepo.findByAlbum(album));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@PathVariable long albumId, @RequestBody Photo input) {
		Album album = this.albumRepo.findOne(albumId);
		if(album == null)
			return ResponseEntity.badRequest().body("Album not found!");
		if(!validate(album))
			return ResponseEntity.status(403).body("Forbidden access to this album!");
		input.setAlbum(album);
		Photo result = this.photoRepo.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(result);
	}
	
	@RequestMapping(path="{photoId}", method = RequestMethod.PUT)
	public ResponseEntity<?> udpate(@PathVariable long albumId, @PathVariable long photoId, @RequestBody Photo input) {
		Album album = this.albumRepo.findOne(albumId);
		if(album == null)
			return ResponseEntity.badRequest().body("Album not found!");
		if(!validate(album))
			return ResponseEntity.status(403).body("Forbidden access to this album!");
		input.setId(photoId);
		input.setAlbum(album);
		Photo result = null;
		try {
			result = this.photoRepo.save(input);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error updating photot: "+e.getMessage());
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long albumId, @PathVariable long photoId) {
		Album album = this.albumRepo.findOne(albumId);
		if(album == null)
			return ResponseEntity.badRequest().body("Album not found!");
		if(!validate(album))
			return ResponseEntity.status(403).body("Forbidden access to this album!");
		try {
			this.photoRepo.delete(photoId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error deleting photo: "+e.getMessage());
		}
		return ResponseEntity.ok("Photo deleted successfully!");
	}
	
	/**
	 * 
	 */
	private boolean validate(Album album) {
		String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return album.getUserId().equals(this.userRepo.findByName(username).getId());
	}
}
