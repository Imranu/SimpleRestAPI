package com.example.springbootfcc.service;

import com.example.springbootfcc.dao.PersonDao;
import com.example.springbootfcc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return this.personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return this.personDao.getAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return this.personDao.getPersonById(id);
    }

    public int updatePersonById(UUID id, Person person) {
        return this.personDao.updatePersonById(id, person);
    }

    public int deletePersonById(UUID id) {
        return this.personDao.deletePersonById(id);
    }

}