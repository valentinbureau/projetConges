package conges.projetConges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import conges.projetConges.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
