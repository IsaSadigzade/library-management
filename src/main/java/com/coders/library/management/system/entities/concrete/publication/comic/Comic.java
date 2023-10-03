package com.coders.library.management.system.entities.concrete.publication.comic;

import com.coders.library.management.system.entities.concrete.publication.InventoryItem;
import com.coders.library.management.system.entities.concrete.publication.Publication;

public class Comic extends Publication {
    private ComicDetail comicDetail;
    private InventoryItem inventoryItem;
    public Comic(int id, String name, String author, double price, double rating, ComicDetail comicDetail, InventoryItem inventoryItem) {
        super(id, name, author, price, rating, comicDetail, inventoryItem);
        this.comicDetail = comicDetail;
        this.inventoryItem = inventoryItem;
    }
}
