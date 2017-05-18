package inst.an.photoalbummanager.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Album {
	private Long userId;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	@OneToMany(mappedBy = "album")
	@JsonManagedReference
	private Set<Photo> photos;

	public Album() {
	}
	public Album(Long userId, Long id, String title) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
}
