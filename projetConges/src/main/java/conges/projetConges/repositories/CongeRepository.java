package conges.projetConges.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import conges.projetConges.entities.Conge;

public interface CongeRepository extends JpaRepository<Conge, Integer>{

	@Query("select c from Conge c where c.demandeur.login.login LIKE :login")
	Optional<List<Conge>> findCongesByLogin(@Param("login")String login);

	@Query("select c from Conge c where c.demandeur.nom LIKE :nom")
	Optional<List<Conge>> findCongesByNomEmploye(@Param("nom") String nom);
	
	
}


