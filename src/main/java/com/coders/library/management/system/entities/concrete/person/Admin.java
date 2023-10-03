package com.coders.library.management.system.entities.concrete.person;

import com.coders.library.management.system.enums.person.Gender;
import com.coders.library.management.system.enums.person.Permission;
import com.coders.library.management.system.enums.person.Role;

import java.util.List;

public class Admin extends Person{

    public Admin(int id, Role role, String name, String surname, String email, String password, Gender gender) {
        super(id, role, name, surname, email, password, gender);
        getPermissions().addAll(List.of(Permission.values()));
    }
}
