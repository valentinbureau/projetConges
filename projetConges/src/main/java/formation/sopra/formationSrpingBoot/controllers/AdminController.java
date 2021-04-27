package formation.sopra.formationSrpingBoot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import formation.sopra.formationSrpingBoot.annotations.IsAdmin;

@RequestMapping("/admin")
@IsAdmin
@Controller
public class AdminController {
	@GetMapping({ "", "/" })
	public ModelAndView index() {
		return new ModelAndView("admin/index");
	}
}
