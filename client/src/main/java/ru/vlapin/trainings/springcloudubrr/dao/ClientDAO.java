package ru.vlapin.trainings.springcloudubrr.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vlapin.trainings.springcloudubrr.model.ClientEntity;

@Component
@RequiredArgsConstructor
public class ClientDAO {

  ClientRepository repo;

  public ClientEntity create(String name) {
    return repo.save(new ClientEntity(name));
  }

  public boolean update(Integer id, String name) {
    return repo.findById(id).stream()
        .peek(clientEntity -> clientEntity.setName(name))
        .peek(repo::save)
        .findAny().isPresent();
  }
}
