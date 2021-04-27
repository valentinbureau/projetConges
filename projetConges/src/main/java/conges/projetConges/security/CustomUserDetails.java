package conges.projetConges.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import conges.projetConges.entities.Login;

public class CustomUserDetails implements UserDetails {

	private Login login;

	public CustomUserDetails(Login login) {
		this.login = login;
	}

	public Login getLogin() {
		return login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		login.getRoles().stream().forEach(rl -> {
			authorities.add(new SimpleGrantedAuthority(rl.getRole().toString()));
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return login.getPassword();
	}

	@Override
	public String getUsername() {
		return login.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// je ne traite pas cette fonction=>je renvoie true pour ne pas etre bloque
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return login.isEnable();
	}

}
