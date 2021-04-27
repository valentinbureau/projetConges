package formation.sopra.formationSrpingBoot.entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "module")
@SequenceGenerator(name = "seqModule", sequenceName = "seq_module", allocationSize = 1, initialValue = 100)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqModule")
	private Integer code;
	@Column(name = "nom", length = 100)
	@NotEmpty
	private String nom;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@NotEmpty
	@Column(name = "details")
	private String details;
	@Min(value = 1)
	@Column(name = "duree")
	private Integer duree;
	@Version
	private int version;

	public Module() {

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Module other = (Module) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
