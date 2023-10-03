package com.coders.library.management.system.database.abstrac;

import com.coders.library.management.system.business.abstarct.UserInputs;
import com.coders.library.management.system.entities.concrete.person.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao extends UserInputs,  DbOperations<Person>, PersonCheckingOperations {
    void createMethod() throws SQLException;

    void save(Person person) throws SQLException;

    void update(Person person) throws SQLException;

    void delete(int id) throws SQLException;

    List<Person> getAllPersons() throws SQLException;

    List<Person> searchByParam(String paramName, String paramValue) throws SQLException;

    int getLastIdNumber();
}
