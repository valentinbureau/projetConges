package formation.sopra.formationSrpingBoot.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

import formation.sopra.formationSrpingBoot.entities.Formation;
import formation.sopra.formationSrpingBoot.entities.Module;
import formation.sopra.formationSrpingBoot.entities.ModuleFormation;
import formation.sopra.formationSrpingBoot.entities.ModuleFormationPK;
import formation.sopra.formationSrpingBoot.repositories.FormateurRepository;
import formation.sopra.formationSrpingBoot.repositories.FormationRepository;
import formation.sopra.formationSrpingBoot.repositories.ModuleFormationRepository;
import formation.sopra.formationSrpingBoot.repositories.ModuleRepository;

@Controller
@RequestMapping("/formation")
public class FormationController {
	@Autowired
	private FormationRepository formationRepository;
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private ModuleFormationRepository moduleFormationRepository;

	@GetMapping({ "", "/" })
	public ModelAndView list() {
		return new ModelAndView("formation/list", "formations", formationRepository.findAll());
	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Integer id) {
		formationRepository.deleteById(id);
		return new ModelAndView("redirect:/formation");
	}

	@GetMapping("/add")
	public ModelAndView add() {
		return goForm(new Formation());
	}

	private ModelAndView goForm(Formation formation) {
		formation.setDateFormation(LocalDate.now());
		ModelAndView modelAndView = new ModelAndView("formation/add", "formation", formation);
		modelAndView.addObject("formateurs", formateurRepository.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute Formation formation, BindingResult br) {
		if (br.hasErrors()) {
			return goForm(formation);
		}
		System.out.println(formation.getDateFormation());
		formationRepository.save(formation);
		return new ModelAndView("redirect:/formation");
	}

	@GetMapping("/details")
	public ModelAndView details(@RequestParam Integer id) {
		Formation formation = formationRepository.findByIdWithModules(id).get();
		ModuleFormation moduleFormation = new ModuleFormation();
		moduleFormation.setId(new ModuleFormationPK(formation, new Module()));
		return goDetails(formation, moduleFormation, moduleRepository.findAll());
	}

	@PostMapping("/details/saveModule")
	public ModelAndView saveModule(@ModelAttribute ModuleFormation moduleFormation) {
		if (moduleFormation.getIntervenant().getId() == null) {
			moduleFormation.setIntervenant(null);
		}
		moduleFormationRepository.save(moduleFormation);
		return new ModelAndView("redirect:/formation/details?id=" + moduleFormation.getId().getFormation().getId());
	}

	@GetMapping("/details/deleteModule")
	public ModelAndView deleteModule(@RequestParam(name = "idModule") Integer idModule,
			@RequestParam(name = "idFormation") Integer idFormation) {
		Formation formation = new Formation();
		formation.setId(idFormation);
		Module module = new Module();
		module.setCode(idModule);
		moduleFormationRepository.deleteById(new ModuleFormationPK(formation, module));
		return new ModelAndView("redirect:/formation/details?id=" + idFormation);
	}

	@GetMapping("/details/editModule")
	public ModelAndView editModule(@RequestParam(name = "idModule") Integer idModule,
			@RequestParam(name = "idFormation") Integer idFormation) {
		Formation formation = formationRepository.findByIdWithModules(idFormation).get();
		Module module = moduleRepository.findById(idModule).get();
		ModuleFormation moduleFormation = moduleFormationRepository.findById(new ModuleFormationPK(formation, module))
				.get();
		return goDetails(formation, moduleFormation, Arrays.asList(moduleFormation.getId().getModule()));

	}

	private ModelAndView goDetails(Formation formation, ModuleFormation moduleFormation, List<Module> modules) {
		ModelAndView modelAndView = new ModelAndView("formation/details");
		modelAndView.addObject("formation", formation);
		modelAndView.addObject("formateurs", formateurRepository.findAll());
		modelAndView.addObject("modules", modules);
		modelAndView.addObject("moduleFormation", moduleFormation);
		return modelAndView;
	}
}
