package com.coders.library.management.system.business.abstarct;

import com.coders.library.management.system.entities.concrete.person.Person;
import com.coders.library.management.system.entities.concrete.person.User;

public interface PersonManager extends PersonOperations<Person>, UserInputs {
    void insertPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Person person);

    void getPersonList();

    boolean idExists(int personId);
}
