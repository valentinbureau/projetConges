package conges.projetConges.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import conges.projetConges.entities.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Integer>{
	
	@Query("select e from Employe e where e.login.login LIKE :login")
	Optional<Employe> findByLogin(@Param("login")String login);
}
