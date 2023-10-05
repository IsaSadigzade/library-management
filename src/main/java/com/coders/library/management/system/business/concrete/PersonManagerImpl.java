package com.coders.library.management.system.business.concrete;

import com.coders.library.management.system.business.abstarct.PersonManager;
import com.coders.library.management.system.database.abstrac.PersonDao;
import com.coders.library.management.system.entities.concrete.person.Person;
import com.coders.library.management.system.entities.concrete.person.User;
import com.coders.library.management.system.enums.person.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonManagerImpl implements PersonManager {
    private final PersonDao personDao;
    List<Person> personList = new ArrayList<>();

    public PersonManagerImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void addPerson(Person person) {
        personDao.save(person);
        personList.add(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDao.update(person);
    }

    @Override
    public void deletePerson(Person person) {
        // TODO: eger istifadeci sadece varsa ve tarixcesinde hec bir emeliyyat yoxdursa ve balansi 0-dirsa, onda hemin istifadecini sil
        personDao.delete(person.getId());
    }

    public Person getPersonById(int personId) {
        List<Person> result = personDao.searchByParam(String.valueOf(PersonDbFields.ID), String.valueOf(personId));
        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            System.out.println("User with ID " + personId + " not found.");
        }
        return null;
    }

    @Override
    public void getPersonList() {
        personDao.getAllPersons();
    }

    @Override
    public boolean idExists(int personId) {
        for (Person p : personList) {
            if (personId == p.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void login() {
        System.out.println("Welcome to login page. If you are not member, please register before login");
        System.out.print("Select 1 for Register: \nSelect 2 for Login: ");
        int operation = INPUT_INT();
        if (operation == 1) {
            register();
        } else if (operation != 2) {
            System.out.println("Invalid operation. Please retry again");
        } else {
            System.out.print("Email: ");
            String email = INPUT_STRING();
            System.out.print("Password: ");
            String password = INPUT_STRING();

            personDao.checkAndStartByEmailAndPassword(email, password);
        }
    }

    @Override
    public void loginDirectly(Person person) {
        personDao.checkAndStartByEmailAndPassword(person.getEmail(), person.getPassword());
    }

    @Override
    public void register() {
        int id = personDao.getLastIdNumber() + 1;
        final Role role = Role.USER;
        System.out.print("Name: ");
        String name = INPUT_STRING();
        System.out.print("Surname: ");
        String surname = INPUT_STRING();
        System.out.print("Email: ");
        String email = INPUT_STRING();
        System.out.print("Password: ");
        String password = INPUT_STRING();
        System.out.print("Gender (Male/Female): ");
        Gender gender = null;
        String inputGender = INPUT_STRING().trim().toLowerCase();
        if (inputGender.equals("male")) {
            gender = Gender.MALE;
        } else if (inputGender.equals("female")) {
            gender = Gender.FEMALE;
        } else {
            System.out.print("Invalid gender type. Please retry register.");
        }
        MembershipStatus membershipStatus = MembershipStatus.NEW_USER;
        System.out.print("What is your membership type? (Young/Adult): ");
        MembershipType membershipType = null;
        String inputType = INPUT_STRING().trim().toLowerCase();
        if (inputType.equals("young")) {
            membershipType = MembershipType.YOUNG_MEMBERSHIP;
        } else if (inputType.equals("adult")) {
            membershipType = MembershipType.ADULT_MEMBERSHIP;
        } else {
            System.out.println("Invalid membership type. Please retry register.");
        }
        double accountBalance = 0;
        final LocalDate creationDate = LocalDate.now();

        Person person = new User(id, role, name, surname, email, password, gender, membershipStatus, membershipType, accountBalance, creationDate);
        addPerson(person);
        loginDirectly(person);
    }
}
