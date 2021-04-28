package conges.projetConges.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;

	// regle pour url open
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**");
	}

	// regle pour url secure
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//// @formatter:off
		http
			.antMatcher("/api/**")
				.csrf().ignoringAntMatchers("/api","/api/**")
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS).anonymous()
				.and()
				.authorizeRequests()
					.antMatchers("/api","/api/**")
					.authenticated()
					.and()
					.httpBasic();
//			.and()
//			.antMatcher("/**")
//				.csrf().ignoringAntMatchers("/api","/api/**")
//				.and()
//				.authorizeRequests()
//					.antMatchers("/login","/logout", "/public", "/public/**","/anonymous","/error").permitAll()
//					.antMatchers("/admin","/admin/**").hasAnyRole("ADMIN")
//					.anyRequest().authenticated();
//			.and()
//			.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/public")
//				.failureUrl("/login?error")
//			.and()
//			.logout()
//				.logoutUrl("/logout")
//				.logoutSuccessUrl("/public");
//		// @formatter:on
	}

	// user
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
