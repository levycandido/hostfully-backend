package com.hostfully.web.rest;

import com.hostfully.entity.Person;
import com.hostfully.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person result = personService.createPerson(person);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person result = personService.getPerson(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Void> getByEmail(@PathVariable String email) {
        personService.getByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> result = personService.getAllPersons();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person result = personService.updatePerson(id, person);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
