package formation.sopra.formationSrpingBoot.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import formation.sopra.formationSrpingBoot.security.CustomUserDetails;

@Controller
public class AnnotationController {

	@Secured({ "ROLE_USER" })
	@GetMapping("/annotation")
	public String accesAvecSecured() {
		return "annotation/page";
	}

	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@GetMapping("/annotation2")
	public String accesAvecPreAuthorize() {
		return "annotation/page";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/authenticated")
	public String authenticated(@AuthenticationPrincipal CustomUserDetails cUD) {
		System.out.println("login dans userDetails:" + cUD.getLogin());
		return "annotation/page";
	}

//	public String authenticated(Authentication authentication) {
//		System.out.println(authentication);
//		CustomUserDetails cUD = (CustomUserDetails) authentication.getPrincipal();
//		System.out.println("login dans userDetails:" + cUD.getLogin());
//		return "annotation/page";
//	}

	@PreAuthorize("isAnonymous()")
	@GetMapping("/anonymous")
	public String anonymous() {
		return "annotation/page";
	}
}
