package com.example.springbootfcc.dao;

import com.example.springbootfcc.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return getPersonById(id).map(existingPerson -> {
            int indexOfPersonToUpdate = DB.indexOf(existingPerson);
            if (indexOfPersonToUpdate >= 0) {
                DB.set(indexOfPersonToUpdate, new Person(id, person.getName()));
                return 1;
            }
            return 0;})
                .orElse(0);

    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> optionalPerson = getPersonById(id);
        if (optionalPerson.isEmpty()) {
            return 0;
        }
        DB.remove(optionalPerson.get());
        return 1;
    }

}