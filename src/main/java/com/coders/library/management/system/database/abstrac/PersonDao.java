package com.coders.library.management.system.database.abstrac;

import com.coders.library.management.system.business.abstarct.UserInputs;
import com.coders.library.management.system.entities.concrete.person.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao extends UserInputs,  DbOperations<Person>{
    void createMethod();

    void save(Person person);

    void update(Person person);

    void delete(int id);

    List<Person> getAllPersons();

    List<Person> searchByParam(String paramName, String paramValue);
    void checkAndStartByEmailAndPassword(String email, String password);

    int getLastIdNumber();
}
