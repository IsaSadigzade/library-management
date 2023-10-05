package com.coders.library.management.system.entities.concrete.person;

import com.coders.library.management.system.enums.person.*;

import java.util.HashSet;
import java.util.Set;

public class Person {
    private int id;
    private Role role;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Gender gender;
    private Set<Permission> permissions;
    // TODO: addPerson age or birthday

    public Person(int id, Role role, String name, String surname, String email, String password, Gender gender) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.permissions = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "Person "
                + "\nID: " + getId()
                + "\nROLE: " + getRole()
                + "\nNAME: " + getName()
                + "\nSURNAME: " + getSurname()
                + "\nEMAIL: " + getEmail()
                + "\nPASSWORD: " + getPassword()
                + "\nGENDER: " + getGender() + "\n";
    }
}
