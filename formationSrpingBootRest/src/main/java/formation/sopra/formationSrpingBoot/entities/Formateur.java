package formation.sopra.formationSrpingBoot.entities;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.formationSrpingBoot.controllers.rest.Views;

@Entity
@Table(name = "formateur")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SequenceGenerator(name = "seqFormateur", sequenceName = "seq_formateur", initialValue = 110, allocationSize = 1)
public class Formateur {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqFormateur")
	private Integer id;
	@JsonView(Views.Common.class)
	@NotEmpty
	@Column(name = "prenom", length = 150, nullable = false)
	private String prenom;
	@JsonView(Views.Common.class)
	@Column(name = "nom", length = 150)
	@NotEmpty
	private String nom;
	@JsonView(Views.Formateur.class)
	@OneToMany(mappedBy = "referent")
	private Set<Formation> formations;
	@OneToMany(mappedBy = "intervenant")
	@JsonView(Views.Formateur.class)
	private Set<ModuleFormation> modulesAnimes;
	@Version
	private int version;

	public Formateur() {

	}

	public String getInfos() {
		return id + " " + prenom + " " + nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Formation> getFormations() {
		return formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}

	public Set<ModuleFormation> getModulesAnimes() {
		return modulesAnimes;
	}

	public void setModulesAnimes(Set<ModuleFormation> modulesAnimes) {
		this.modulesAnimes = modulesAnimes;
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
		Formateur other = (Formateur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
