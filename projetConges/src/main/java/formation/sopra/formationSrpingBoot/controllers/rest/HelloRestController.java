package formation.sopra.formationSrpingBoot.controllers.rest;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class HelloRestController {

	@GetMapping("")
	public String sayHello() {
		return "hello world";
	}

	@GetMapping("/data")
	public Data getData() {
		Data data = new Data(1, "olivier");
		data.setList(Arrays.asList(new Data2("aaa", "aaa"), new Data2("bbbb", "bbbb")));
		return data;
	}

	@GetMapping("/boss")
	@JsonView(Views.Common.class)
	public Employe getEmpWithCommon() {
		return getEmp();
	}

	@GetMapping("/boss/manager")
	@JsonView(Views.EmpWithManager.class)
	public Employe getEmpWithManager() {
		return getEmp();
	}

	@GetMapping("/boss/sub")
	@JsonView(Views.ManagerWithSub.class)
	public Employe getManagerWithSub() {
		return getEmp();
	}

	private Employe getEmp() {
		Employe boss = new Employe();
		ArrayList<Employe> empDeBoss = new ArrayList<Employe>();
		boss.setNom("boss");
		boss.setSubodonnes(empDeBoss);
		Employe emp1 = new Employe();
		emp1.setNom("emp1");
		emp1.setManager(boss);
		empDeBoss.add(emp1);

		return boss;
	}

	@GetMapping("/boss/test")
	@JsonView(Views.EmpWithManager.class)
	public Employe getEmpWithTest() {
		return getEmp();
	}

}
