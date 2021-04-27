package formation.sopra.formationSrpingBoot.controllers.rest;

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

import formation.sopra.formationSrpingBoot.entities.Formateur;
import formation.sopra.formationSrpingBoot.exceptions.FormateurInvalidException;
import formation.sopra.formationSrpingBoot.repositories.FormateurRepository;

@RestController
@RequestMapping("/api/formateur")
@CrossOrigin(origins="*")
public class FormateurRestController {

	@Autowired
	private FormateurRepository formateurRepository;
	 
	//CRUD
	
	
	@JsonView(Views.Common.class)
	@GetMapping("")
	public ResponseEntity<List<Formateur>> allFormateur(){
		return new ResponseEntity<List<Formateur>>(formateurRepository.findAll(),HttpStatus.OK);
	}
	
	//Create
	@JsonView(Views.Common.class)
	@PostMapping("")
	public ResponseEntity<Formateur> createFormateur(@Valid @RequestBody Formateur formateur, BindingResult br, 
				UriComponentsBuilder uCB){
		if (br.hasErrors()) {
			throw new FormateurInvalidException();
		}
		formateur = formateurRepository.save(formateur);
		URI uri = uCB.path("/api/formateur/{id}").buildAndExpand(formateur.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Formateur>(formateur, headers, HttpStatus.CREATED);
	}
	
	//findById
	@JsonView(Views.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Formateur> getById(@PathVariable("id") Integer id){
		Optional<Formateur> opt = formateurRepository.findById(id);
		if(!opt.isPresent()) {
			throw new FormateurInvalidException();
		}
		return new ResponseEntity<Formateur>(opt.get(), HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		Optional<Formateur> opt = formateurRepository.findById(id);
		if(opt.isPresent()) {
			formateurRepository.deleteById(id);
		} else {
		throw new FormateurInvalidException();	
		}
	}
	
	//Update
	@JsonView(Views.Common.class)
	@PutMapping("/{id}")
	public ResponseEntity<Formateur> update(@RequestBody Formateur formateur, BindingResult br, @PathVariable("id") Integer id){
		if (br.hasErrors()) {
			throw new FormateurInvalidException();
		}
		Optional<Formateur> opt = formateurRepository.findById(id);
		if (!opt.isPresent()) {
			Formateur formateurEnBase = opt.get();
			formateur.setVersion(formateurEnBase.getVersion());
			formateur.setId(id);
			return new ResponseEntity<Formateur>(formateurRepository.save(formateur), HttpStatus.OK);
		} else {
			throw new FormateurInvalidException();
		}
	}
}
