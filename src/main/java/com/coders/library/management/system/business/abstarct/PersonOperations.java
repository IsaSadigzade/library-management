package com.coders.library.management.system.business.abstarct;

import com.coders.library.management.system.entities.concrete.person.Person;

import java.sql.SQLException;

public interface PersonOperations<Type> {
    void login() throws SQLException;
    void login(Person person) throws SQLException;
    Type register() throws SQLException;

}
