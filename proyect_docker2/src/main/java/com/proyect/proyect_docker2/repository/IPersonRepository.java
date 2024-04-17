package com.proyect.proyect_docker2.repository;

import com.proyect.proyect_docker2.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<Person, Long> {
}
