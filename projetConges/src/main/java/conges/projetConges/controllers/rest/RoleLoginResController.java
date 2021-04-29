package conges.projetConges.controllers.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import conges.projetConges.entities.RoleLogin;
import conges.projetConges.exceptions.EmployeInvalidException;
import conges.projetConges.repositories.RoleLoginRepository;

@RestController
@RequestMapping("/api/rolelogin")
@CrossOrigin(origins="*")
public class RoleLoginResController {

	@Autowired
	private RoleLoginRepository roleLoginRepository;
	
	@GetMapping("/{login}")
	@JsonView(Views.Employe.class)
	public ResponseEntity<RoleLogin> getByLogin(@PathVariable("login") String login){
		Optional<RoleLogin> opt = roleLoginRepository.findByLogin(login);
		if(!opt.isPresent()) {
			throw new EmployeInvalidException();
		}
		return new ResponseEntity<RoleLogin>(opt.get(), HttpStatus.OK);
	}
}
