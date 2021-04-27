package conges.projetConges.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import conges.projetConges.repositories.EmployeRepository;
import conges.projetConges.repositories.LoginRepository;

@Service
public class Console implements CommandLineRunner {

	@Autowired
	private EmployeRepository employeRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		//initPasswordDataBase();
	}

	private void initPasswordDataBase() {
		loginRepository.findAll().stream().forEach(login -> {
			login.setPassword(passwordEncoder.encode(login.getLogin()));
			loginRepository.save(login);
		});
	}

}
