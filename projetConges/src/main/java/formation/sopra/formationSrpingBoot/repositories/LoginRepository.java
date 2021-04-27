package formation.sopra.formationSrpingBoot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.formationSrpingBoot.entities.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
	@Query("select l from Login l left join fetch l.roles where l.login=:login")
	Optional<Login> findByLogin(@Param("login") String login);
}
