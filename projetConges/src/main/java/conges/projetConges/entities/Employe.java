package conges.projetConges.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="employé")
@SequenceGenerator(name= "seqEmploye", sequenceName="seq_employe", initialValue = 110, allocationSize = 1)
public class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEmploye")
	private Integer id;
	
	@NotEmpty
	@Column(name = "prenom", length = 150, nullable = false)
	private String prenom;
	
	@NotEmpty
	@Column(name = "nom", length = 150, nullable = false)
	private String nom;
	
	@ManyToOne
	@JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "employe_id_service_fk"))
	private Service service;
	
	@OneToMany(mappedBy="demandeur")
	private Set<Conge> conges;
	
	@Version
	private int version;

	public Employe() {
	}

	
	public String getInfos() {
		return id + " " + prenom + " " + nom;
	}
	
	// ---------------- Getters & Setters -----------------------//
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	// -------------- HashCode & Equals -----------------//
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
		Employe other = (Employe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
