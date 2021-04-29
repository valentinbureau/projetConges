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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

import conges.projetConges.controllers.rest.Views;

import conges.projetConges.validators.DateDansLeFutur;


@Entity
@Table(name = "conge")
@SequenceGenerator(name = "seqConge", sequenceName = "seq_conge", initialValue = 110, allocationSize = 1)
public class Conge {
	
	@JsonView({Views.Conge.class, Views.Employe.class})
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqConge")
	private Integer id;
	
	@JsonView({Views.Conge.class, Views.Employe.class})
	@Enumerated(EnumType.STRING)
	@Column(name = "typeConge")
	private TypeConge typeConge;
	
	@NotNull
	@JsonView({Views.Conge.class, Views.Employe.class})
	@DateDansLeFutur
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateDebut")
	private LocalDate dateDebut;
	
	@NotNull
	@JsonView({Views.Conge.class, Views.Employe.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateFin")
	private LocalDate dateFin;
	
	@JsonView(Views.Conge.class)
	@Column(name = "motif")
	private String motif;
	@JsonView({Views.Conge.class, Views.Employe.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateDemande")
	private LocalDate dateDemande = LocalDate.now();
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_employe", foreignKey = @ForeignKey(name = "conge_id_employe_fk"))
	@JsonView(Views.Conge.class)
	private Employe demandeur;
	
	@JsonView(Views.Conge.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "statut")
	private Statut statut;
	
	@JsonView(Views.Conge.class)
	@Column(name="raisonRefus")
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

	public void setDateDebut(String dateDebut) {
		this.dateDebut = LocalDate.parse(dateDebut);
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = LocalDate.parse(dateFin);
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

	public void setDateDemande(String dateDemande) {
		this.dateDemande = LocalDate.parse(dateDemande);
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
