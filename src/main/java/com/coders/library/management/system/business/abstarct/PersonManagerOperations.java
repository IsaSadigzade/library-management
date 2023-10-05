package com.coders.library.management.system.business.abstarct;

import com.coders.library.management.system.entities.concrete.person.Person;

public interface PersonManagerOperations {
    void login();
    void loginDirectly(Person person);
    void register();

}
