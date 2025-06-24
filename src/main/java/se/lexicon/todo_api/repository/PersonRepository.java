package se.lexicon.todo_api.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.todo_api.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    // Custom query method to find a person by email
    Optional<Person> findByEmail(String email);

    // Custom query method to check if a person exists by email
    boolean existsByEmail(String email);

    @Override
    List<Person> findAll();
}
