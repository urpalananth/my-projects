package in.inst.photoalbummanager.controller;

import java.net.URI;
import java.util.List;

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

import in.inst.photoalbummanager.beans.Album;
import in.inst.photoalbummanager.repo.AlbumRepository;
import in.inst.photoalbummanager.repo.UserRepository;

@RestController
@RequestMapping("/albums")
public class AlbumController {
	
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Album input) {
		Album result = this.albumRepo.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(result);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> udpate(@PathVariable Long id, @RequestBody Album input) {
		Album result = this.albumRepo.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(result);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		System.out.println("--> "+id);
		try {
			this.albumRepo.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path="/all", method = RequestMethod.GET)
	public List<Album> readAll() {
		return albumRepo.findAll();
	}
}
