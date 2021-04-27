package formation.sopra.formationSrpingBoot.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import formation.sopra.formationSrpingBoot.entities.Login;
import formation.sopra.formationSrpingBoot.repositories.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> opt = loginRepository.findByLogin(username);
		if (opt.isPresent()) {
			return new CustomUserDetails(opt.get());
		}
		throw new UsernameNotFoundException("utilisateur inconnu");
	}

}
