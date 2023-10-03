package com.coders.library.management.system.entities.concrete.publication;

import com.coders.library.management.system.enums.publication.Status;

public class InventoryItem {
    private int quantity;
    private Status status;
    private boolean isSellable;
    private int sold;
    private boolean isBorrowable;
    private int borrowed;
    private boolean isReservable;
    private int reserved;

    public InventoryItem(int quantity, Status status, boolean isSellable, int sold, boolean isBorrowable, int borrowed, boolean isReservable, int reserved) {
        this.quantity = quantity;
        this.status = status;
        this.isSellable = isSellable;
        this.sold = sold;
        this.isBorrowable = isBorrowable;
        this.borrowed = borrowed;
        this.isReservable = isReservable;
        this.reserved = reserved;
    }

    public int getQuantity() {
        return quantity;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isSellable() {
        return isSellable;
    }

    public int getSold() {
        return sold;
    }

    public boolean isBorrowable() {
        return isBorrowable;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public boolean isReservable() {
        return isReservable;
    }

    public int getReserved() {
        return reserved;
    }
}
