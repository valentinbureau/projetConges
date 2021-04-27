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

import formation.sopra.formationSrpingBoot.entities.Module;
import formation.sopra.formationSrpingBoot.repositories.FormationRepository;
import formation.sopra.formationSrpingBoot.repositories.ModuleRepository;

@Controller
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private FormationRepository formationRepository;

	@GetMapping({ "", "/" })
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("module/list");
		modelAndView.addObject("modules", moduleRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam Integer code) {
		Optional<Module> opt = moduleRepository.findById(code);
		if (opt.isPresent()) {
			return goEdit(opt.get());
		}
		return goEdit(new Module());
	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Integer code) {
		moduleRepository.deleteById(code);
		return new ModelAndView("redirect:/module");
	}

	@GetMapping("/add")
	public ModelAndView add() {
		return goEdit(new Module());
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute Module module, BindingResult br) {
		if (br.hasErrors()) {
			return goEdit(module);
		}
		moduleRepository.save(module);
		return new ModelAndView("redirect:/module");
	}

	private ModelAndView goEdit(Module module) {
		ModelAndView modelAndView = new ModelAndView("module/edit", "module", module);
		return modelAndView;
	}
}
