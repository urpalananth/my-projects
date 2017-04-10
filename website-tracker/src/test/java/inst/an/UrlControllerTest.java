package inst.an;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import inst.an.websitetracker.UrlBean;
import inst.an.websitetracker.WebsiteTrackerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebsiteTrackerApplication.class)
@WebAppConfiguration

public class UrlControllerTest {
	
	  @Autowired
	  private WebApplicationContext context;

	  private MockMvc mvc;
	  
	  private static final String BASE_URL = "http://localhost:8080";

	  @Before
	  public void setup() {
	      mvc = MockMvcBuilders
	              .webAppContextSetup(context)
	              .build();
	  }
	  @Test
	  public void testLogin() throws Exception{

			TestRestTemplate testRestTemplate = new TestRestTemplate();
			
			ResponseEntity<UrlBean> response = testRestTemplate.withBasicAuth("ananth", "").getForEntity(BASE_URL+"/urls/1", UrlBean.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
			
			response = testRestTemplate.withBasicAuth("ananth", "ananth").getForEntity(BASE_URL+"/urls/1", UrlBean.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	  }

	  @Test
	  public void testPostURL() throws Exception{

			TestRestTemplate testRestTemplate = new TestRestTemplate();
			UrlBean url = new UrlBean();
			url.setUrl("https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html");
			
			ResponseEntity<UrlBean> response = testRestTemplate
					.withBasicAuth("ananth", "ananth")
					.postForEntity(BASE_URL+"/urls/", url, UrlBean.class);
			
			assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
			
			ResponseEntity<String> responseStr = testRestTemplate.withBasicAuth("ananth", "ananth").getForEntity(response.getHeaders().getLocation(), String.class);
			
			System.out.println("--> "+responseStr.getBody());
	  }
	  
}
