package inst.an.photoalbummanager.controller;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import inst.an.photoalbummanager.repo.AlbumRepository;
import inst.an.photoalbummanager.repo.UserRepository;

@RestController
@RequestMapping("/albums")
public class AlbumController {
	private static final Logger LOGGER = Logger.getLogger("PAMUserDetailsService");
	private AlbumRepository albumRepo;
	private UserRepository userRepo;
	
	@Autowired
	public AlbumController(AlbumRepository albumRepo, UserRepository userRepo) {
		this.albumRepo = albumRepo;
		this.userRepo = userRepo;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Album> read() {
		String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return albumRepo.findByUserId(this.userRepo.findByName(username).getId());
	}
	
	@RequestMapping(path="/{id:[0-9]+}", method = RequestMethod.GET)
	public ResponseEntity<?>  readOne(@PathVariable Long id) {
		Album album = this.albumRepo.findOne(id);
		if(album == null)
			return ResponseEntity.notFound().build();
		if(!validate(album))
			return ResponseEntity.status(403).body("Forbidden access to this album!");
		return ResponseEntity.ok(albumRepo.findOne(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Album input) {
		Album result = this.albumRepo.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(result);
	}
	
	@RequestMapping(path="/{id:[0-9]+}", method = RequestMethod.PUT)
	public ResponseEntity<?> udpate(@PathVariable Long id, @RequestBody Album input) {
		Album result = this.albumRepo.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(result);
	}
	
	@RequestMapping(path="/all", method = RequestMethod.GET)
	public ResponseEntity<?> readAll() {
		String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		if(!username.equals("admin"))
			return ResponseEntity.status(403).body("Access Denied!!");
		return ResponseEntity.ok(albumRepo.findAll());
	}
	
	@RequestMapping(path="/{id:[0-9]+}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Album album = this.albumRepo.findOne(id);
		if(album == null)
			return ResponseEntity.notFound().build();
		if(!validate(album))
			return ResponseEntity.status(403).body("Forbidden access to this album!");
		
		try {
			this.albumRepo.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof DataIntegrityViolationException)
				return ResponseEntity.status(500).body("Album with id - "+id+" has photos, cannot be deleted!");
			return ResponseEntity.status(500).body("Error deleting the album: "+e.getMessage());
		}
		return ResponseEntity.ok("Album deleted successfully!");
	}
	
	/**
	 * 
	 */
	private boolean validate(Album album) {
		String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return album.getUserId().equals(this.userRepo.findByName(username).getId());
	}
}
