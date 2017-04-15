package inst.an.websitetracker.controllers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import inst.an.websitetracker.beans.UrlBean;
import inst.an.websitetracker.repo.UrlBeanRepository;

@Controller
@RequestMapping("/urls")
public class UrlController {

	private UrlBeanRepository urlRepo;
	
	@Autowired
	public UrlController(UrlBeanRepository urlRepo) {
		super();
		this.urlRepo = urlRepo;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody UrlBean input) {
		URL url = null;
		try {
			url = new URL(input.getUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
		String domain = url.getHost();
		
		String [] parts = domain.split("\\.");
		
		int numberOfParts = 2;
		
		String newDomain = "";
		
		for (int i = parts.length-1; i >= 0 && numberOfParts > 0; i--) {
			numberOfParts--;
			newDomain += parts[i];
			if(numberOfParts != 0)
				newDomain += ".";
		}
		
//		System.out.println("last index - " + domain.lastIndexOf("."));
//		
//		System.out.println("2nd from last - "+domain.lastIndexOf(".", domain.lastIndexOf(".", domain.lastIndexOf(".")-1)-1));
//		
//		domain = domain.substring(domain.lastIndexOf(".", domain.lastIndexOf(".", domain.lastIndexOf(".")-1)-1)+1);
//		
//		System.out.println("just domain & top level domain - "+domain);
		
		input.setDomain(newDomain);
		
		System.out.println("create request received ...");
		UrlBean result = urlRepo.save(input);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(result);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{urlId}")
	public @ResponseBody UrlBean get(@PathVariable String urlId) {
		System.out.println("request received ...");
		return urlRepo.findOne(Long.valueOf(urlId));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/top3")
	public @ResponseBody List<Map<String, Object>> getTop3() {
		System.out.println("request received ...");
		
		List<Object[]> top3 = this.urlRepo.findTop3Visited();
		Map<String, Object> top3Map = null;
		List<Map<String, Object>> top3List = new ArrayList<>();
		
		for (Object[] objects : top3) {
			top3Map = new LinkedHashMap<>();
			top3Map.put("domain", objects[0]);
			top3Map.put("hits", objects[1]);
			top3List.add(top3Map);
		}
		
		return top3List;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<UrlBean> getAll() {
		return this.urlRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/domainCounts")
	public @ResponseBody List<Map<String, Object>> getDomainCounts() {
		System.out.println("request received ...");
		
		List<Object[]> top3 = this.urlRepo.getDomainsAndCount();
		Map<String, Object> top3Map = null;
		List<Map<String, Object>> top3List = new ArrayList<>();
		
		for (Object[] objects : top3) {
			top3Map = new LinkedHashMap<>();
			top3Map.put("domain", objects[0]);
			top3Map.put("hits", objects[1]);
			top3List.add(top3Map);
		}
		
		return top3List;
	}
}
