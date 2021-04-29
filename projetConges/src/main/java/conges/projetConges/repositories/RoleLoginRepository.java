package conges.projetConges.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import conges.projetConges.entities.RoleLogin;

public interface RoleLoginRepository extends JpaRepository<RoleLogin, Integer>{

	
	@Query("select rl from RoleLogin rl where rl.login.login LIKE :login")
	Optional<RoleLogin> findByLogin(@Param("login") String login);
	
}
