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
import conges.projetConges.entities.Employe;
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
	@JsonView(Views.Service.class)
	public ResponseEntity<List<Service>> allServices(){
		return new ResponseEntity<List<Service>>(serviceRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/manager/{id}")
	@JsonView(Views.Service.class)
	public ResponseEntity<Employe> getManager(@PathVariable("id") Integer id ){
		return new ResponseEntity<Employe>(serviceRepository.getManager(id),HttpStatus.OK);
	}

	//Create
	@PostMapping("")
	@JsonView(Views.Service.class)
	public ResponseEntity<Service> createService(@Valid @RequestBody Service service, BindingResult br, 
			UriComponentsBuilder uCB){
		if (br.hasErrors()) {
			throw new ServiceInvalidException();
		}
		service = serviceRepository.save(service);
		URI uri = uCB.path("/api/service/{id}").buildAndExpand(service.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		Optional<Employe> m = employeRepository.findById(service.getManager().getId());
		Employe ma=m.get();
		ma.setService(service);
		ma= employeRepository.save(m.get());
		return new ResponseEntity<Service>(service, headers, HttpStatus.CREATED);
	}

	//findById
	@GetMapping("/{id}")
	@JsonView(Views.Service.class)
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
	@JsonView(Views.Service.class)
	public void deleteById(@PathVariable("id") Integer id) {
		Optional<Service> opt = serviceRepository.findById(id);
		if(opt.isPresent()) {
			serviceToNull(opt.get());
			serviceRepository.deleteById(id);
		} else {
			throw new ServiceInvalidException();	
		}
	}

	//Update
	@PutMapping("/{id}")
	@JsonView(Views.Service.class)
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

	@JsonView(Views.Service.class)
	@PatchMapping("/{id}")
	public Service update(@RequestBody Map<String, Object> attributs, @PathVariable Integer id) {
		Optional<Service> opt = serviceRepository.findById(id);
	if (!opt.isPresent()) {
		throw new ServiceInvalidException();
	}
	Service service = opt.get();
	attributs.forEach((key, value) -> {
		Field field = ReflectionUtils.findField(Service.class, key);
		ReflectionUtils.makeAccessible(field);
		if (key.equals("manager")) {
			Map<String, Object> map = (Map<String, Object>) value;
			if (map != null) {
				if (map.get("id") != null) {
					Employe manager = employeRepository.findById(Integer.parseInt(map.get("id").toString()))
							.get();
					service.setManager(manager);
				} else {
					throw new ServiceInvalidException();
				}
			} else {
				ReflectionUtils.setField(field, service, value);
			}
		}

		else {
			ReflectionUtils.setField(field, service, value);
		}
	});

	return serviceRepository.save(service);

}

	private void serviceToNull(Service service)
	{
		List<Employe> employes = employeRepository.findAll();
		for (Employe e : employes)
		{
			if (e.getService()!=null && e.getService().getId()==service.getId())
			{
				e.setService(null);
				employeRepository.save(e);
			}
		}
	}


}
