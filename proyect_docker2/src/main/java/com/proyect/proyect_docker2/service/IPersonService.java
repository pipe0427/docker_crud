package com.proyect.proyect_docker2.service;

import com.proyect.proyect_docker2.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPersonService {
    public ResponseEntity<List<Person>> searchPerson();

    public ResponseEntity<List<Person>> savePerson(Person person);

    public ResponseEntity<List<Person>> updatePerson(Person person, Long id);

    public ResponseEntity<List<Person>> deletePerson(Long id);

    public ResponseEntity<Person> searchPersonById(Long id);
}
