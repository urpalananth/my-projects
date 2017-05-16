package inst.an.photoalbummanager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import inst.an.photoalbummanager.PhotoAlbumManagerApplication;
import inst.an.photoalbummanager.beans.Album;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhotoAlbumManagerApplication.class)
@WebAppConfiguration

public class AlbumControllerTest {
		private static final Logger LOGGER = Logger.getLogger("PAMUserDetailsService");	  
	  private static final String BASE_URL = "http://localhost:8080";
	  
	  @Test
	  public void testLogin() throws Exception{

			TestRestTemplate testRestTemplate = new TestRestTemplate();
			
			ResponseEntity<?> response = testRestTemplate.withBasicAuth("ananth", "").getForEntity(BASE_URL+"/init", String.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
			
			response = testRestTemplate.withBasicAuth("ab", "ab").getForEntity(BASE_URL+"/init", String.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	  }

	  @Test
	  public void testCreateAlbum() throws Exception{

			TestRestTemplate testRestTemplate = new TestRestTemplate();
			
			Album a = new Album();
			a.setUserId(2l);
			a.setTitle("ananth's 1");
			
			ResponseEntity<Album> response = testRestTemplate
					.withBasicAuth("ab", "ab")
					.postForEntity(BASE_URL+"/albums/", a, Album.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
			
			ResponseEntity<Album> responseStr = testRestTemplate.withBasicAuth("ab", "ab")
					.getForEntity(response.getHeaders().getLocation(), Album.class);
			
			assertThat(responseStr.getStatusCode(), equalTo(HttpStatus.OK));

			assertThat("ananth's 1", equalTo(responseStr.getBody().getTitle()));
	  }
	  
	  @Test
	  public void testDeleteAlbum() throws Exception{
		  RestTemplate restTemplate = new RestTemplate();
		  restTemplate.getInterceptors().add(
				  new BasicAuthorizationInterceptor("ab", "ab"));

		Album a = new Album();
		a.setUserId(2l);
		a.setTitle("ananth's 1");
		// Create the album
		ResponseEntity<Album> response = restTemplate.postForEntity(BASE_URL + "/albums/", a, Album.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
		// Get and see if the album is created
		String newAlbumPath = response.getHeaders().getLocation().toString();
		LOGGER.info("newAlbumPath : " + newAlbumPath);
		ResponseEntity<?> response1 = restTemplate.getForEntity(newAlbumPath, Album.class);

		assertThat(response1.getStatusCode(), equalTo(HttpStatus.OK));

		restTemplate.delete(newAlbumPath);

		try {
			response1 = restTemplate.getForEntity(newAlbumPath, String.class);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("404"));
		}
		try {
			restTemplate.delete(BASE_URL + "/albums/11");
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("500"));
		}
	  }
}
