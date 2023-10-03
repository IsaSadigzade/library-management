package com.coders.library.management.system.entities.concrete.person;

import com.coders.library.management.system.enums.person.Gender;
import com.coders.library.management.system.enums.person.Role;

public class SubAdmin extends Person{
    public SubAdmin(int id, Role role, String name, String surname, String email, String password, Gender gender) {
        super(id, role, name, surname, email, password, gender);
    }
}
