package formation.sopra.formationSrpingBoot.controllers.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class Employe {
	@JsonView(Views.Common.class)
	private String nom;
	@JsonView({ Views.EmpWithManager.class, Views.Test.class })
	private Employe manager;
	@JsonView(Views.ManagerWithSub.class)
	private List<Employe> subodonnes;
	private String pasDeVueAssocie;

	public String getPasDeVueAssocie() {
		return pasDeVueAssocie;
	}

	public void setPasDeVueAssocie(String pasDeVueAssocie) {
		this.pasDeVueAssocie = pasDeVueAssocie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Employe getManager() {
		return manager;
	}

	public void setManager(Employe manager) {
		this.manager = manager;
	}

	public List<Employe> getSubodonnes() {
		return subodonnes;
	}

	public void setSubodonnes(List<Employe> subodonnes) {
		this.subodonnes = subodonnes;
	}

}
