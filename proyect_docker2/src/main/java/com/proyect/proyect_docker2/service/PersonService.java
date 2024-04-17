package com.proyect.proyect_docker2.service;

import com.proyect.proyect_docker2.model.Person;
import com.proyect.proyect_docker2.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {
    @Autowired
    private IPersonRepository repository;

    private List<Person> persons = new ArrayList<>();

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Person>> searchPerson() {
        try {
            persons = (List<Person>) repository.findAll();
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(persons, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Person>> savePerson(Person person) {
        try {
            Person personSaved = repository.save(person);

            if (personSaved != null) {
                persons.add(personSaved);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<Person>> updatePerson(Person person,Long id) {
        try {
            Optional<Person> personSearch = repository.findById(id);

            if (personSearch.isPresent()) {
                personSearch.get().setName(person.getName());
                personSearch.get().setSurname(person.getSurname());
                personSearch.get().setPhone(person.getPhone());

                Person personsSaved = repository.save(personSearch.get());

                if (personsSaved != null) {
                    persons.add(personsSaved);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<Person>> deletePerson(Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Person> searchPersonById(Long id) {
        try {
            Optional<Person> person = repository.findById(id);
            if(person.isPresent()) {
                return new ResponseEntity<>(person.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
