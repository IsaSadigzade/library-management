package com.coders.library.management.system.business.abstarct;

import com.coders.library.management.system.entities.concrete.person.Person;

public interface PersonManager extends PersonManagerOperations, UserInputs {
    void addPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Person person);

    void getPersonList();

    boolean idExists(int personId);

}
