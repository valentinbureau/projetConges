package conges.projetConges.controllers.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import conges.projetConges.entities.Conge;
import conges.projetConges.exceptions.CongeInvalidException;
import conges.projetConges.repositories.CongeRepository;

@RestController
@RequestMapping("/api/conge")
@CrossOrigin(origins="*")
public class CongeRestController {

	@Autowired
	private CongeRepository congeRepository;
	
	@GetMapping("")
	@JsonView(Views.Conge.class)
	public ResponseEntity<List<Conge>> allConges(){
		return new ResponseEntity<List<Conge>>(congeRepository.findAll(),HttpStatus.OK);
	}
	
	
	//Create
	@PostMapping("")
	@JsonView(Views.Conge.class)
	public ResponseEntity<Conge> createConge(@Valid @RequestBody Conge conge, BindingResult br, 
				UriComponentsBuilder uCB){
		if (br.hasErrors()) {
			throw new CongeInvalidException();
		}
		conge = congeRepository.save(conge);
		URI uri = uCB.path("/api/conge/{id}").buildAndExpand(conge.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Conge>(conge, headers, HttpStatus.CREATED);
	}
	
	//findById
	@JsonView(Views.Conge.class)
	@GetMapping("/{id}")
	public ResponseEntity<Conge> getById(@PathVariable("id") Integer id){
		Optional<Conge> opt = congeRepository.findById(id);
		if(!opt.isPresent()) {
			throw new CongeInvalidException();
		}
		return new ResponseEntity<Conge>(opt.get(), HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@JsonView(Views.Conge.class)
	public void deleteById(@PathVariable("id") Integer id) {
		Optional<Conge> opt = congeRepository.findById(id);
		if(opt.isPresent()) {
			congeRepository.deleteById(id);
		} else {
		throw new CongeInvalidException();	
		}
	}
	
	//Update
	@JsonView(Views.Conge.class)
	@PutMapping("/{id}")
	public ResponseEntity<Conge> update(@RequestBody Conge conge, BindingResult br, @PathVariable("id") Integer id){
		if (br.hasErrors()) {
			throw new CongeInvalidException();
		}
		Optional<Conge> opt = congeRepository.findById(id);
		if (opt.isPresent()) {
			Conge congeEnBase = opt.get();
			conge.setVersion(congeEnBase.getVersion());
			conge.setId(id);
			return new ResponseEntity<Conge>(congeRepository.save(conge), HttpStatus.OK);
		} else {
			throw new CongeInvalidException();
		}
	}
	
	@JsonView(Views.Conge.class)
	@GetMapping("/employe/{login}")
	public ResponseEntity<List<Conge>> allCongesByEmploye(@PathVariable("login") String login){
		
		Optional<List<Conge>> opt = congeRepository.findCongesByLogin(login);
		System.out.println(opt.get());
		if(!opt.isPresent()) {
			throw new CongeInvalidException();
		}
		return new ResponseEntity<List<Conge>>(opt.get(), HttpStatus.OK);
	}
	
	
}
