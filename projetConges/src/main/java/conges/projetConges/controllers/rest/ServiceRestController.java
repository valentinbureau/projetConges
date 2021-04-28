package conges.projetConges.controllers.rest;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import conges.projetConges.entities.Conge;
import conges.projetConges.entities.Service;
import conges.projetConges.exceptions.EmployeInvalidException;
import conges.projetConges.exceptions.ServiceInvalidException;
import conges.projetConges.repositories.EmployeRepository;
import conges.projetConges.repositories.ServiceRepository;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins="*")
public class ServiceRestController {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private EmployeRepository employeRepository;

	@GetMapping("")
	public ResponseEntity<List<Service>> allServices(){
		return new ResponseEntity<List<Service>>(serviceRepository.findAll(),HttpStatus.OK);
	}

	//Create
	@PostMapping("")
	public ResponseEntity<Service> createService(@Valid @RequestBody Service service, BindingResult br, 
			UriComponentsBuilder uCB){
		if (br.hasErrors()) {
			throw new ServiceInvalidException();
		}
		service = serviceRepository.save(service);
		URI uri = uCB.path("/api/service/{id}").buildAndExpand(service.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Service>(service, headers, HttpStatus.CREATED);
	}

	//findById
	@GetMapping("/{id}")
	public ResponseEntity<Service> getById(@PathVariable("id") Integer id){
		Optional<Service> opt = serviceRepository.findById(id);
		if(!opt.isPresent()) {
			throw new ServiceInvalidException();
		}
		return new ResponseEntity<Service>(opt.get(), HttpStatus.OK);
	}

	//Delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		Optional<Service> opt = serviceRepository.findById(id);
		if(opt.isPresent()) {
			serviceRepository.deleteById(id);
		} else {
			throw new ServiceInvalidException();	
		}
	}

	//Update
	@PutMapping("/{id}")
	public ResponseEntity<Service> update(@RequestBody Service service, BindingResult br, @PathVariable("id") Integer id){
		if (br.hasErrors()) {
			throw new ServiceInvalidException();
		}
		Optional<Service> opt = serviceRepository.findById(id);
		if (!opt.isPresent()) {
			Service serviceEnBase = opt.get();
			service.setVersion(serviceEnBase.getVersion());
			service.setId(id);
			return new ResponseEntity<Service>(serviceRepository.save(service), HttpStatus.OK);
		} else {
			throw new ServiceInvalidException();
		}
	}

	@JsonView(Views.Common.class)
	@PatchMapping("/{id}")
	public Service update(@RequestBody Map<String, Object> fields, @PathVariable("id") Integer id) {
		Optional<Service> opt = serviceRepository.findById(id);
		if (opt.isPresent()) {
			Service service = opt.get();
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Service.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, service, value);
			});
			return serviceRepository.save(service);
		}
		else
		{
			throw new ServiceInvalidException();
		}
	}



}
