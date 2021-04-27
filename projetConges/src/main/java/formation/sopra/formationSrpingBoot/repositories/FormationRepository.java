package formation.sopra.formationSrpingBoot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.formationSrpingBoot.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation, Integer> {
	@Query("select f from Formation f left join fetch f.modules left join fetch f.referent where f.id=:id")
	public Optional<Formation> findByIdWithModules(@Param("id") Integer id);
}
