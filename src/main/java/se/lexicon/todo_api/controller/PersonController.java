package se.lexicon.todo_api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
@Validated  // validation annotation is used to enable validation on the controller methods.
//@Valid // can be put above the class instead inside the parameter list.
public class PersonController {
    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //GET  http://localhost:8080/api/v1/person
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // What response status to give if successfully.
    public List<PersonDto> getPersons() {
        return personService.findAll();
    }

    //GET  http://localhost:8080/api/v1/person/2
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(
            @PathVariable("id")
            @Positive(message = "Id must be positive number.") // Validation annotation, auto checks if the parameter is a positive number
            Long personId
    ) {
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
    public PersonDto createPerson(
            @RequestBody
            @NotNull(message = "Person can not be null.")
            // @Valid before a parameter will enable all the validation annotations in the class of the object for this method.
            @Valid PersonDto personDto) {
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
