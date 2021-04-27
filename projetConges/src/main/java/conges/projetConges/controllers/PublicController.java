package conges.projetConges.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/public")
@Controller
public class PublicController {

	@GetMapping({ "", "/" })
	public ModelAndView index() {
		return new ModelAndView("public/index");
	}

	@GetMapping("/page1")
	public ModelAndView page1() {
		return new ModelAndView("public/page1");
	}
}
