package conges.projetConges.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import conges.projetConges.controllers.rest.Views;

@Entity
@Table(name = "service")
@SequenceGenerator(name = "seqService", sequenceName = "seq_service", initialValue = 110, allocationSize = 1)
public class Service {
	
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqService")
	private Integer id;
	
	@JsonView(Views.Common.class)
	@NotEmpty
	@Column(name = "nom", length = 150, nullable = false)
	private String nom;
	
	@NotEmpty
	@OneToOne(mappedBy="service")
	private Employe manager;
	
	@OneToMany(mappedBy = "service")
	private Set<Employe> listeEmployes;

	@Version
	private int version;
	
	public Service() {
	}
	
	// ----------------- Getters & Setters ------------------//

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

	public Employe getManager() {
		return manager;
	}

	public void setManager(Employe manager) {
		this.manager = manager;
	}

	public Set<Employe> getListeEmployes() {
		return listeEmployes;
	}

	public void setListeEmployes(Set<Employe> listeEmployes) {
		this.listeEmployes = listeEmployes;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
