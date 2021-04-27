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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import conges.projetConges.entities.Employe;
import conges.projetConges.exceptions.EmployeInvalidException;
import conges.projetConges.repositories.EmployeRepository;

@RestController
@RequestMapping("/api/employe")
@CrossOrigin(origins="*")
public class EmployeRestController {

	@Autowired
	private EmployeRepository employeRepository;
	
	@GetMapping("")
	public ResponseEntity<List<Employe>> allEmployes(){
		return new ResponseEntity<List<Employe>>(employeRepository.findAll(),HttpStatus.OK);
	}
	
	//Create
	@PostMapping("")
	public ResponseEntity<Employe> createEmploye(@Valid @RequestBody Employe employe, BindingResult br, 
				UriComponentsBuilder uCB){
		if (br.hasErrors()) {
			throw new EmployeInvalidException();
		}
		employe = employeRepository.save(employe);
		URI uri = uCB.path("/api/employe/{id}").buildAndExpand(employe.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Employe>(employe, headers, HttpStatus.CREATED);
	}
	
	//findById
	@JsonView(Views.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Employe> getById(@PathVariable("id") Integer id){
		Optional<Employe> opt = employeRepository.findById(id);
		if(!opt.isPresent()) {
			throw new EmployeInvalidException();
		}
		return new ResponseEntity<Employe>(opt.get(), HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		Optional<Employe> opt = employeRepository.findById(id);
		if(opt.isPresent()) {
			employeRepository.deleteById(id);
		} else {
		throw new EmployeInvalidException();	
		}
	}
	
	//Update
	@JsonView(Views.Common.class)
	@PutMapping("/{id}")
	public ResponseEntity<Employe> update(@RequestBody Employe employe, BindingResult br, @PathVariable("id") Integer id){
		if (br.hasErrors()) {
			throw new EmployeInvalidException();
		}
		Optional<Employe> opt = employeRepository.findById(id);
		if (!opt.isPresent()) {
			Employe employeEnBase = opt.get();
			employe.setVersion(employeEnBase.getVersion());
			employe.setId(id);
			return new ResponseEntity<Employe>(employeRepository.save(employe), HttpStatus.OK);
		} else {
			throw new EmployeInvalidException();
		}
	}
}
