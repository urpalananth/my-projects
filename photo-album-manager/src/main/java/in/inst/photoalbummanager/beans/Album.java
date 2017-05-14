package in.inst.photoalbummanager.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Album {
	private long userId;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;
	@OneToMany(cascade = CascadeType.ALL, 
    mappedBy = "album", orphanRemoval = true)
	@JsonBackReference
//	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Photo> photos;

	public Album() {
	}
	public Album(long userId, long id, String title, Set<Photo> photos) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.photos = photos;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
