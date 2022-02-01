package com.example.springbootfcc.api;

import com.example.springbootfcc.model.Person;
import com.example.springbootfcc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Create
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        this.personService.addPerson(person);
    }

    // Read All
    @GetMapping
    public List<Person> getAllPeople() {
        return this.personService.getAllPeople();
    }

    // Read by ID
    @GetMapping(path = "/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return this.personService.getPersonById(id).orElse(null);
    }

    // Updated by ID
    @PutMapping(path = "/{id}")
    public Optional<Person> updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person person) {
        this.personService.updatePersonById(id, person);
        return this.personService.getPersonById(id);
    }

    // Delete by ID
    @DeleteMapping(path = "/{id}")
    public boolean deletePersonById(@PathVariable("id") UUID id) {
        return this.personService.deletePersonById(id) == 1;
    }

}
