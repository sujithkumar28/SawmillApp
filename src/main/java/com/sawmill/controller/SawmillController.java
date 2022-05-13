package com.sawmill.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.sawmill.exception.ResourceNotFoundException;
import com.sawmill.model.Sawmill;
import com.sawmill.service.SawmillService;


@RestController
@RequestMapping("/sawmill")
@CrossOrigin
public class SawmillController {
	
	Logger logger = LoggerFactory.getLogger(SawmillController.class);

	@Autowired
	private SawmillService sawmillService;

	@GetMapping
	public List<Sawmill> listAllSawMill() {
		logger.trace("Endpoint : /listAllSawmill");
		List<Sawmill> listOfSawMill = sawmillService.findAll(Sort.by(Sort.Direction.ASC, "name"));

		return listOfSawMill;
	}

	@GetMapping("/{id}")
	public Sawmill getSawMill(@PathVariable int id) throws ResourceNotFoundException {
		logger.trace("Endpoint : /getSawmill/"+id);
		return sawmillService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sawmill does not exist with Id : " + id));
	}

	@PostMapping()
	public ResponseEntity<Sawmill> createSawmill(@Valid @RequestBody Sawmill sawMill) throws URISyntaxException {
		logger.trace("Endpoint : /createSawmill");
		
		sawMill.setCreatedAt(new Date());
		Sawmill newSawMillCreated = sawmillService.save(sawMill);
		logger.trace("sawmill object : "+newSawMillCreated);
		if (newSawMillCreated == null) {
			return ResponseEntity.notFound().build();
		} else {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newSawMillCreated.getId()).toUri();

			return ResponseEntity.created(uri).body(newSawMillCreated);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Sawmill> updateSawmill(@Valid @RequestBody Sawmill sawMill,
			@PathVariable(value = "id") int id) throws Exception {
		logger.trace("Endpoint : /updateSawmill/"+id);
		Sawmill updatedSawMill = null;

		Sawmill existingSawMill = this.sawmillService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sawmill does not exist"));
		existingSawMill.setCity(sawMill.getCity());
		existingSawMill.setCountry(sawMill.getCountry());
		existingSawMill.setUpdatedAt(new Date());
		updatedSawMill = sawmillService.save(existingSawMill);

		if (updatedSawMill == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(updatedSawMill);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteSawmill(@PathVariable("id") int id) throws ResourceNotFoundException{
		logger.trace("Endpoint : /deleteSawMill/"+id);
		Sawmill existingSawMill = this.sawmillService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sawmill does not exist"));
		sawmillService.delete(existingSawMill);
		return ResponseEntity.noContent().build();
	}

}
