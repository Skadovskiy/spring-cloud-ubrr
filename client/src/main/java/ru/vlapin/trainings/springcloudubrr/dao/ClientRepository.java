package ru.vlapin.trainings.springcloudubrr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.trainings.springcloudubrr.model.ClientEntity;


public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}
