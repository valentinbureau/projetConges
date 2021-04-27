package formation.sopra.formationSrpingBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.formationSrpingBoot.entities.ModuleFormation;
import formation.sopra.formationSrpingBoot.entities.ModuleFormationPK;

public interface ModuleFormationRepository extends JpaRepository<ModuleFormation, ModuleFormationPK> {

}
