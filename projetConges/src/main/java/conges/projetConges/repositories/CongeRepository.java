package conges.projetConges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import conges.projetConges.entities.Conge;

public interface CongeRepository extends JpaRepository<Conge, Integer>{

}
