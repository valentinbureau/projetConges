package formation.sopra.formationSrpingBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.formationSrpingBoot.entities.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Integer> {
	
}
