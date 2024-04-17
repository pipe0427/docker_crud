package com.proyect.proyect_docker2.controller;

import com.proyect.proyect_docker2.model.Person;
import com.proyect.proyect_docker2.service.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/")
public class PersonController {
    public IPersonService service;

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        return service.searchPerson();
    }

    @PostMapping("persons")
    public ResponseEntity<List<Person>> savePerson(@RequestParam("name") String name,@RequestParam("surname") String surname, @RequestParam("phone") String phone) {
        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        person.setPhone(phone);
        return service.savePerson(person);
    }

    @PutMapping("persons/{personId}")
    public ResponseEntity<List<Person>> updatePerson(@RequestParam("name") String name,@RequestParam("surname") String surname, @RequestParam("phone") String phone,@PathVariable Long personId) {
        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        person.setPhone(phone);
        return service.updatePerson(person,personId);
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<List<Person>> deletePerson(@PathVariable Long id) {
        return service.deletePerson(id);
    }
}
