package formation.sopra.formationSrpingBoot.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import formation.sopra.formationSrpingBoot.entities.Formateur;
import formation.sopra.formationSrpingBoot.repositories.FormateurRepository;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

	@Autowired
	private FormateurRepository formateurRepository;

	@GetMapping({ "", "/" })
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("formateur/list");
		modelAndView.addObject("formateurs", formateurRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam Integer id) {
		Optional<Formateur> opt = formateurRepository.findById(id);
		if (opt.isPresent()) {
			return goEdit(opt.get());
		}
		return goEdit(new Formateur());
	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Integer id) {
		formateurRepository.deleteById(id);
		return new ModelAndView("redirect:/formateur");
	}

	@GetMapping("/add")
	public ModelAndView add() {
		return goEdit(new Formateur());
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute Formateur formateur, BindingResult br) {
		System.out.println(br);
		if (br.hasErrors()) {
			return goEdit(formateur);
		}
		formateurRepository.save(formateur);
		return new ModelAndView("redirect:/formateur");
	}

	private ModelAndView goEdit(Formateur formateur) {
		ModelAndView modelAndView = new ModelAndView("formateur/edit", "formateur", formateur);
		return modelAndView;
	}

	
}
