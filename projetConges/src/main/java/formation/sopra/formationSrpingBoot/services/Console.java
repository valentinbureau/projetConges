package formation.sopra.formationSrpingBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import formation.sopra.formationSrpingBoot.repositories.FormateurRepository;
import formation.sopra.formationSrpingBoot.repositories.LoginRepository;

@Service
public class Console implements CommandLineRunner {

	@Autowired
	private FormateurRepository formateurRepository;

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
