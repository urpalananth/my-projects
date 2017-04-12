package inst.an;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import inst.an.websitetracker.WebsiteTrackerApplication;
import inst.an.websitetracker.beans.UrlBean;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebsiteTrackerApplication.class)
@WebAppConfiguration

public class UrlControllerTest {
	  
	  private static final String BASE_URL = "http://localhost:8080";
	  
	  @Test
	  public void testLogin() throws Exception{

			TestRestTemplate testRestTemplate = new TestRestTemplate();
			
			ResponseEntity<UrlBean> response = testRestTemplate.withBasicAuth("ananth", "").getForEntity(BASE_URL+"/urls/1", UrlBean.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
			
			response = testRestTemplate.withBasicAuth("user", "password").getForEntity(BASE_URL+"/urls/1", UrlBean.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	  }

	  @Test
	  public void testPostURL() throws Exception{

			TestRestTemplate testRestTemplate = new TestRestTemplate();
			UrlBean url = new UrlBean();
			url.setUrl("https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html");
			
			ResponseEntity<UrlBean> response = testRestTemplate
					.withBasicAuth("user", "password")
					.postForEntity(BASE_URL+"/urls/", url, UrlBean.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
			
			ResponseEntity<String> responseStr = testRestTemplate.withBasicAuth("user", "password").getForEntity(response.getHeaders().getLocation(), String.class);
			
			assertThat(responseStr.getStatusCode(), equalTo(HttpStatus.OK));
			
			System.out.println("--> "+responseStr.getBody());
	  }
	  
	  @Test
	  public void testGetTop3() throws Exception{

		  	TestRestTemplate testRestTemplate = new TestRestTemplate();
			UrlBean url = new UrlBean();
			url.setUrl("https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html");
			
			ResponseEntity<UrlBean> response = testRestTemplate
					.withBasicAuth("user", "password")
					.postForEntity(BASE_URL+"/urls/", url, UrlBean.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
			
			
			ResponseEntity<String> response2 = testRestTemplate
					.withBasicAuth("user", "password")
					.getForEntity(BASE_URL+"/urls/top3", String.class);
			
			assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
	  }	  
}
