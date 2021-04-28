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

import conges.projetConges.entities.Login;
import conges.projetConges.exceptions.LoginInvalidException;
import conges.projetConges.repositories.LoginRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {
	
	@Autowired
	private LoginRepository loginRepository; 

	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/login")
	public void login() {}
	
	//List
	@GetMapping("")
	public ResponseEntity<List<Login>> allLogin(){
		return new ResponseEntity<List<Login>>(loginRepository.findAll(),HttpStatus.OK);
	}
	
	//Create
	@PostMapping("")
	public ResponseEntity<Login> createEmploye(@Valid @RequestBody Login login, BindingResult br, 
				UriComponentsBuilder uCB){
		if (br.hasErrors()) {
			throw new LoginInvalidException();
		}
		login = loginRepository.save(login);
		URI uri = uCB.path("/api/login/{id}").buildAndExpand(login.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Login>(login, headers, HttpStatus.CREATED);
	}
	
	//Delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		Optional<Login> opt = loginRepository.findById(id);
		if(opt.isPresent()) {
			loginRepository.deleteById(id);
		} else {
		throw new LoginInvalidException();	
		}
	}
		
	//Update
	@JsonView(Views.Common.class)
	@PutMapping("/{id}")
	public ResponseEntity<Login> update(@RequestBody Login login, BindingResult br, @PathVariable("id") Integer id){
		if (br.hasErrors()) {
			throw new LoginInvalidException();
		}
		Optional<Login> opt = loginRepository.findById(id);
		if (!opt.isPresent()) {
			Login loginEnBase = opt.get();
			login.setVersion(loginEnBase.getVersion());
			login.setId(id);
			return new ResponseEntity<Login>(loginRepository.save(login), HttpStatus.OK);
		} else {
			throw new LoginInvalidException();
		}
	}
}
