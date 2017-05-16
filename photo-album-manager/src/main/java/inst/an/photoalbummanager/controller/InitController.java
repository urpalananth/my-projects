package inst.an.photoalbummanager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import inst.an.photoalbummanager.beans.Album;
import inst.an.photoalbummanager.beans.AlbumData;
import inst.an.photoalbummanager.beans.Photo;
import inst.an.photoalbummanager.beans.PhotoData;
import inst.an.photoalbummanager.repo.AlbumRepository;
import inst.an.photoalbummanager.repo.PhotoRepository;

@Controller
@RequestMapping("/init")
public class InitController {
	private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
	
	private PhotoRepository photoRepo;
	private AlbumRepository albumRepo;
	
	
	@Autowired
	public InitController(PhotoRepository photoRepo, AlbumRepository albumRepo) {
		super();
		this.photoRepo = photoRepo;
		this.albumRepo = albumRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> initialize() {
		enableSSL();
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ResponseEntity<AlbumData[]> albums = restTemplate.getForEntity(BASE_URL + "albums", AlbumData[].class);
			ResponseEntity<PhotoData[]> photos = restTemplate.getForEntity(BASE_URL + "photos", PhotoData[].class);

			List<AlbumData> albumList = Arrays.asList(albums.getBody());
			List<PhotoData> photoList = Arrays.asList(photos.getBody());

			System.out.println("--> " + albumList.size());
			System.out.println("--> " + photoList.size());

			List<Album> aList = albumList
					.stream().map(
							a -> new Album(a.getUserId(), a.getId(),
									a.getTitle()))
					.collect(Collectors.toList());
			
			aList = albumRepo.save(aList);
			
			List<Photo> pList = photoList.stream()
									.map(c -> new Photo(c.getId(), c.getTitle(), c.getUrl(), c.getThumbnailUrl(), 
											this.albumRepo.findOne(c.getAlbumId())))
									.collect(Collectors.toList());
					
			this.photoRepo.save(pList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error while initializing: "+e.getCause());
		}
		
		System.out.println("--> "+albumRepo.count());
		System.out.println("--> "+photoRepo.count());
		return ResponseEntity.ok("Photo Album Manager data initialized !!");
	}
	
    private void enableSSL() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
 
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
 
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };
 
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
    }
}
