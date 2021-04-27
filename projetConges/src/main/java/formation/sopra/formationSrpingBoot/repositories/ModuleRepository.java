package formation.sopra.formationSrpingBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.formationSrpingBoot.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
	
}
