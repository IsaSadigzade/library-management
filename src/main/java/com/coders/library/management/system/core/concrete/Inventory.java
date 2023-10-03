package com.coders.library.management.system.core.concrete;

import com.coders.library.management.system.business.abstarct.CommonManager;
import com.coders.library.management.system.business.abstarct.PersonManager;
import com.coders.library.management.system.business.abstarct.PublicationManager;
import com.coders.library.management.system.business.concrete.CommonManagerImpl;
import com.coders.library.management.system.business.concrete.PersonManagerImpl;
import com.coders.library.management.system.business.abstarct.UserInputs;
import com.coders.library.management.system.business.concrete.PublicationManagerImpl;
import com.coders.library.management.system.database.abstrac.PersonDao;
import com.coders.library.management.system.database.abstrac.PublicationDao;
import com.coders.library.management.system.database.concrete.PersonDb;
import com.coders.library.management.system.database.concrete.PublicationDb;
import com.coders.library.management.system.entities.concrete.person.Admin;
import com.coders.library.management.system.entities.concrete.person.Person;
import com.coders.library.management.system.entities.concrete.person.SubAdmin;
import com.coders.library.management.system.entities.concrete.person.User;

import java.sql.SQLException;
import java.util.List;

public class Inventory implements UserInputs {
    List<Person> personDbList;
    private PersonDao personDao;

    public Inventory() {
        personDao = new PersonDb();
    }

    List<Person> getInventoryPersons() throws SQLException {
        personDbList = personDao.getAllPersons();
        return personDbList;
    }
//    private final PersonDao personDao = new PersonDb(this);
    private final PersonManager personManager = new PersonManagerImpl(personDao);

    private final PublicationDao publicationDao = new PublicationDb();
    private final PublicationManager publicationManager = new PublicationManagerImpl(publicationDao);
    private final CommonManager commonManager = new CommonManagerImpl();

    public void start() {
        System.out.println("Please login to continue");
        try {
            personManager.login();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void inventoryMenu(Person person) {
        System.out.println("Welcome to Inventory Menu " + person.getName());
        System.out.println("Which operation want you do?");
        System.out.println("1. User Operations");
        System.out.println("2. Publication Operations");
        switch (INPUT_INT()) {
            case 1 -> userOperations(person);
            case 2 -> publicationOperations();
            case 133 -> officialOperations(person);
        }
    }

    private void officialOperations(Person person) {
        if ((person instanceof Admin) || (person instanceof SubAdmin)) {
            System.out.println("?");
        }
    }

    private void userOperations(Person person) {
        System.out.println("Which user operation want you do?");
        System.out.println("1. Look for balance");
        System.out.println("2. Add balance");
        switch (INPUT_INT()) {
            case 1 -> lookForBalance((User) person);
            case 2 -> addBalance((User) person);
        }
    }

    private void lookForBalance(User user) {
        System.out.println("Your balance: " + user.getAccountBalance());
    }

    private void addBalance(User user) {
        System.out.println("Add your money: ");
        double addedBalance = INPUT_INT();
        if (addedBalance > 0) {
            user.setAccountBalance(user.getAccountBalance() + addedBalance);
            personManager.updatePerson(user);
            System.out.println(addedBalance + " added to you balance");
        } else {
            System.out.println("Invalid Balance Operation");
        }
        userOperations(user);
    }

    private void publicationOperations() {
        System.out.println("Which publication operation want you do?");
        System.out.println("1. Look publications");
        System.out.println("2. Buy publications");
        switch (INPUT_INT()) {
            case 1 -> lookPublications();
            case 2 -> buyPublications();
        }
    }

    private void lookPublications() {
        System.out.println(personDbList);
    }

    private void buyPublications() {

    }
}
