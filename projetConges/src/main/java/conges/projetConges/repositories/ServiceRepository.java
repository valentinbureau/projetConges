package conges.projetConges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import conges.projetConges.entities.Employe;
import conges.projetConges.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

	@Query("select s.manager from Service s where s.id=:id")
	Employe getManager(@Param("id")Integer id);

}
