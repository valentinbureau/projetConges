package conges.projetConges.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import conges.projetConges.validators.DateDansLeFutur;

@Entity
@Table(name = "conge")
@SequenceGenerator(name = "seqConge", sequenceName = "seq_conge", initialValue = 110, allocationSize = 1)
public class Conge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqConge")
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(name = "typeConge")
	private TypeConge typeConge;
	
	@DateDansLeFutur
	private LocalDate dateDebut;
	
	
	private LocalDate dateFin;
	private String motif;
	private LocalDate dateDemande = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name = "idconge", foreignKey = @ForeignKey(name = "conge_id_employe_fk"))
	private Employe demandeur;
	@Enumerated(EnumType.STRING)
	@Column(name = "statut")
	private Statut statut;
	
	private String RaisonRefus;
	@Version
	private int version;
	
	public Conge() {
	}

	// ------------------ Getters & Setters ---------------------//
	
	
	public TypeConge getTypeConge() {
		return typeConge;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employe getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Employe demandeur) {
		this.demandeur = demandeur;
	}

	public void setTypeConge(TypeConge typeConge) {
		this.typeConge = typeConge;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public LocalDate getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(LocalDate dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getRaisonRefus() {
		return RaisonRefus;
	}

	public void setRaisonRefus(String raisonRefus) {
		RaisonRefus = raisonRefus;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
	
}
