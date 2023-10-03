package com.coders.library.management.system.entities.concrete.publication;

import com.coders.library.management.system.enums.publication.Category;
import com.coders.library.management.system.enums.publication.Language;
import com.coders.library.management.system.enums.publication.PublicationType;
import com.coders.library.management.system.enums.publication.Status;

import java.time.LocalDate;

public class Publication {
    private int id;
    private String name;
    private String author;
    private double price;
    private double rating;
    private PublicationDetail publicationDetail;
    private InventoryItem inventoryItem;

    public Publication(int id, String name, String author, double price, double rating, PublicationDetail publicationDetail, InventoryItem inventoryItem) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.publicationDetail = publicationDetail;
        this.inventoryItem = inventoryItem;
    }

    public Publication(int id, PublicationType type, String name, String author, float price, float rating, Category category,
                       Language language, int pageCount, String publisher, LocalDate publicationDate, int quantity, Status status,
                       boolean isSellable, int sold, boolean isBorrowable, int borrowed, boolean isReservable, int reserved) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.publicationDetail = new PublicationDetail(type, category, language, pageCount, publisher, publicationDate);
        this.inventoryItem = new InventoryItem(quantity, status, isSellable, sold, isBorrowable, borrowed, isReservable, reserved);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public PublicationDetail getPublicationDetail() {
        return publicationDetail;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    @Override
    public String toString() {
        return "Publication "
                + "\nID: " + getId()
                + "\nNAME: " + getName()
                + "\nAUTHOR: " + getAuthor()
                + "\nPRICE: " + getPrice()
                + "\nRATING: " + getRating()
                + "\nTYPE: " + getPublicationDetail().getPublicationType()
                + "\nCATEGORY: " + getPublicationDetail().getCategory()
                + "\nLANGUAGE: " + getPublicationDetail().getLanguage()
                + "\nPAGE_COUNT: " + getPublicationDetail().getPageCount()
                + "\nPUBLISHER: " + getPublicationDetail().getPublisher()
                + "\nPUBLICATION_DATE: " + getPublicationDetail().getPublicationDate()
                + "\nQUANTITY: " + getInventoryItem().getQuantity()
                + "\nSTATUS: " + getInventoryItem().getStatus() + "\n";
    }
}
