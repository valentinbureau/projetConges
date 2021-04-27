package formation.sopra.formationSrpingBoot.controllers.rest;

import java.lang.reflect.Field;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

import formation.sopra.formationSrpingBoot.entities.Formation;
import formation.sopra.formationSrpingBoot.exceptions.FormationInvalidException;
import formation.sopra.formationSrpingBoot.repositories.FormationRepository;

@RestController
@RequestMapping("/api/formation")
@CrossOrigin(origins = "*")
public class FormationRestController {

	@Autowired
	private FormationRepository formationRepository;

	// crud
	// all formations
	
	//set ce qu'on va voir sur le Json, en lien avec les attributs de l'entité associée
	@JsonView(Views.Common.class)
	@GetMapping("")
	public List<Formation> allFormation() {
		return formationRepository.findAll();
	}

	public ResponseEntity<List<Formation>> allFormationV2() {
		return new ResponseEntity<List<Formation>>(formationRepository.findAll(), HttpStatus.OK);
	}

	// creation
//	@PostMapping("")
//	@JsonView(Views.Common.class)
//	@ResponseStatus(HttpStatus.CREATED)
//	public Formation createFormation(@Valid @RequestBody Formation formation, BindingResult br) {
//		if (br.hasErrors()) {
//			throw new FormationInvalidException();
//		}
//		return formationRepository.save(formation);
//	}

	@PostMapping("")
	@JsonView(Views.Common.class)
	public ResponseEntity<Formation> createFormation(@Valid @RequestBody Formation formation, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			throw new FormationInvalidException();
		}
		formation = formationRepository.save(formation);
		URI uri = uCB.path("/api/formation/{id}").buildAndExpand(formation.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Formation>(formation, headers, HttpStatus.CREATED);

	}

	// update

	// delete

	// findById
	@JsonView(Views.Common.class)
	@GetMapping("/search")
	public Formation getById(@RequestParam Integer id) {
		Optional<Formation> opt = formationRepository.findById(id);
		if (!opt.isPresent()) {
			throw new FormationInvalidException();
		}
		return opt.get();

	}

	@JsonView(Views.Common.class)
	@GetMapping("/{id}")
	public Formation getByIdWithPathVarialbe(@PathVariable("id") Integer id) {
		Optional<Formation> opt = formationRepository.findById(id);
		if (!opt.isPresent()) {
			throw new FormationInvalidException();
		}
		return opt.get();

	}

	@JsonView(Views.Common.class)
	@GetMapping("/{id}/search")
	public Formation getByIdWithPathVarialbeAndRequestParam(@PathVariable("id") Integer id,
			@RequestParam(name = "nom", required = false) String nom) {
		Optional<Formation> opt = formationRepository.findById(id);
		if (!opt.isPresent()) {
			throw new FormationInvalidException();
		}
		return opt.get();

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		Optional<Formation> opt = formationRepository.findById(id);
		if (opt.isPresent()) {
			formationRepository.deleteById(id);
		} else {
			throw new FormationInvalidException();
		}
	}

	@JsonView(Views.Common.class)
	@PutMapping("/{id}")
	public Formation update(@Valid @RequestBody Formation formation, BindingResult br, @PathVariable("id") Integer id) {
		Optional<Formation> opt = formationRepository.findById(id);
		if (opt.isPresent()) {
			Formation formationEnBase = opt.get();
			formation.setVersion(formationEnBase.getVersion());
			formation.setId(id);
			formation.setNom(formation.getNom().toLowerCase());
			return formationRepository.save(formation);
		}
		throw new FormationInvalidException();
	}

	@PatchMapping("/{id}")
	@JsonView(Views.Common.class)
	public Formation update(@RequestBody Map<String, Object> attributs, @PathVariable("id") Integer id) {
		Optional<Formation> opt = formationRepository.findById(id);
		if (!opt.isPresent()) {
			throw new FormationInvalidException();
		}
		Formation formation = opt.get();
		attributs.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Formation.class, key);
			ReflectionUtils.makeAccessible(field);
			if (key.equals("dateFormation")) {
				formation.setDateFormation(LocalDate.parse(value.toString()));
			} else {
				ReflectionUtils.setField(field, formation, value);
			}
		});
		formationRepository.save(formation);
		return formation;
	}

}
