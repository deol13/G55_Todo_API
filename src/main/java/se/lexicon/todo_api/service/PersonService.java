package se.lexicon.todo_api.service;

import se.lexicon.todo_api.dto.PersonDto;

import java.util.List;

public interface PersonService {

    // Method to find all persons
    List<PersonDto> findAll();

    PersonDto findById(Long id);

    PersonDto create(PersonDto personDto);

    void delete(Long id);
}
