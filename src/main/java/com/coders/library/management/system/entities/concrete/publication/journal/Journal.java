package com.coders.library.management.system.entities.concrete.publication.journal;

import com.coders.library.management.system.entities.concrete.publication.InventoryItem;
import com.coders.library.management.system.entities.concrete.publication.Publication;

public class Journal extends Publication {
    private JournalDetail journalDetail;
    private InventoryItem inventoryItem;
    public Journal(int id, String name, String author, double price, double rating, JournalDetail journalDetail, InventoryItem inventoryItem) {
        super(id, name, author, price, rating, journalDetail, inventoryItem);
        this.journalDetail = journalDetail;
        this.inventoryItem = inventoryItem;
    }
}
