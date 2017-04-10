package inst.an.websitetracker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlBean {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String url;
	private String domain;
	private long hits;
	public UrlBean() {
	}
	public UrlBean(Long id, String url, String domain, long hits) {
		super();
		this.url = url;
		this.domain = domain;
		this.hits = hits;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public long getHits() {
		return hits;
	}
	public void setHits(long hits) {
		this.hits = hits;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", url=" + url + ", domain=" + domain + ", hits=" + hits + "]";
	}
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		UrlBean url = (UrlBean)obj;
		return this.id.equals(url.id);
	}
}