package ru.vlapin.trainings.springcloudubrr.controller;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.trainings.springcloudubrr.dao.ClientDAO;
import ru.vlapin.trainings.springcloudubrr.dao.ClientRepository;
import ru.vlapin.trainings.springcloudubrr.model.Client;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientRest {

  ClientDAO dao;
  ClientRepository repository;

  @Contract(pure = true)
  @PostMapping// @RequestMapping(method = POST)
  public @NotNull ResponseEntity<Client> create(@NotNull @RequestParam("name") String name) {
    return ok(dao.create(name));
  }

  @PutMapping("{id}")
  public @NotNull ResponseEntity<Object> update(@PathVariable int id,
                                                @NotNull @RequestParam("name") String name) {
    return (dao.update(id, name) ? noContent() : notFound()).build();
  }

  @DeleteMapping("{id}")
  public @NotNull ResponseEntity<Object> delete(@PathVariable int id) {
    repository.deleteById(id);
    return accepted().build();
  }

  @GetMapping
  public @NotNull ResponseEntity<? extends Collection<? extends Client>> getAll() {
    return ResponseEntity.ok(repository.findAll());
  }

  @GetMapping("{id}")
  public @NotNull ResponseEntity<? extends Client> get(@PathVariable int id) {
    return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
