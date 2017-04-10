package inst.an.websitetracker.controllers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import inst.an.websitetracker.UrlBean;
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
		
		input.setDomain(url.getHost());
		
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
	public @ResponseBody Collection<UrlBean> getTop3() {
		System.out.println("request received ...");
		return this.urlRepo.findTop3Visited();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<UrlBean> getAll() {
		return this.urlRepo.findAll();
	}
}
