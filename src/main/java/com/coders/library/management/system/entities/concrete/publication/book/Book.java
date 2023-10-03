package com.coders.library.management.system.entities.concrete.publication.book;

import com.coders.library.management.system.entities.concrete.publication.InventoryItem;
import com.coders.library.management.system.entities.concrete.publication.Publication;

public class Book extends Publication {
    private BookDetail bookDetail;
    private InventoryItem inventoryItem;

    public Book(int id, String name, String author, double price, double rating, BookDetail bookDetail, InventoryItem inventoryItem) {
        super(id, name, author, price, rating, bookDetail, inventoryItem);
        this.bookDetail = bookDetail;
        this.inventoryItem = inventoryItem;
    }

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

}
