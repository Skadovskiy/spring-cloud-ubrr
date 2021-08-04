package ru.vlapin.trainings.springcloudubrr.model;

import static lombok.AccessLevel.PROTECTED;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.Contract;

@Getter
@Entity
@ToString
@Setter//(PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ClientEntity implements Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @NonNull String name;

  @Override
  @Contract(value = "null -> false", pure = true)
  public boolean equals(Object o) {
    return this == o || o != null
                            && Hibernate.getClass(this) == Hibernate.getClass(o)
                            && o instanceof ClientEntity clientEntity
                            && Objects.equals(id, clientEntity.id);
  }

  @Override
  public int hashCode() {
    return 1225950929;
  }
}
