package com.coders.library.management.system;

import com.coders.library.management.system.core.concrete.Inventory;

public class App {
    // TODO: add special methods for own classes
    // TODO: sehv email, password check edilmelidi
    // TODO: sifrenizi unutduz deyib updateMethod istifade edilmelidi

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.start();

//        PublicationDb publicationDb = new PublicationDb();
//        publicationDb.createMethod();
//        PersonDb personDb = new PersonDb(inventory);
//        personDb.createMethod();
    }
}
