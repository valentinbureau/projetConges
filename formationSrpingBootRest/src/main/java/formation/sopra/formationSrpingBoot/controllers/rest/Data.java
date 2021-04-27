package formation.sopra.formationSrpingBoot.controllers.rest;

import java.util.List;

public class Data {
	private Integer id;
	private String nom;
	private List<Data2> list;
	private List<Data> listData;

	public Data() {

	}

	public Data(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Data2> getList() {
		return list;
	}

	public void setList(List<Data2> list) {
		this.list = list;
	}

}
