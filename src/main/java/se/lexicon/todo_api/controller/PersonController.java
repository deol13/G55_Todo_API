package se.lexicon.todo_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todo_api.dto.PersonDto;
import se.lexicon.todo_api.entity.Person;
import se.lexicon.todo_api.repository.PersonRepository;
import se.lexicon.todo_api.service.PersonService;

import java.util.List;

@RestController
// indicated that this class is a REST controller
// It will handle HTTP requests and responses.
@RequestMapping("/api/v1/person")
public class PersonController {
    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //GET  http://localhost:8080/api/v1/person
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersons() {
        return personService.findAll();
    }

    //GET  http://localhost:8080/api/v1/person/2
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable("id") Long personId) {
        System.out.println("personId = " + personId);
        return personService.findById(personId);
    }

    //POST  http://localhost:8080/api/v1/person
    /*
    Request Body:
    {
        id: 0,
        name: "John Doe",
        email: "test@test.se"
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        System.out.println("personDto = " + personDto);
        return personService.create(personDto);
    }


    //DELETE  http://localhost:8080/api/v1/person/2
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deletePerson(@PathVariable("id") Long personId) {
        System.out.println("personId = " + personId);
        personService.delete(personId);
    }

}
