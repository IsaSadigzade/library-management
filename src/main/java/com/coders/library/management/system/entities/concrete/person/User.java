package com.coders.library.management.system.entities.concrete.person;

import com.coders.library.management.system.enums.person.Gender;
import com.coders.library.management.system.enums.person.MembershipStatus;
import com.coders.library.management.system.enums.person.MembershipType;
import com.coders.library.management.system.enums.person.Role;
import com.coders.library.management.system.entities.concrete.publication.book.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private MembershipStatus membershipStatus;
    private MembershipType membershipType;
    private LocalDate creationDate;
    private double accountBalance;
    private List<Book> boughtBooks;
    private List<Book> borrowedBooks;
    private List<Book> reservedBooks;
    private List<Book> returnedBooks;
    private List<Book> historyOfUser;

    public User(int id, Role role, String name, String surname, String email, String password, Gender gender, MembershipStatus membershipStatus,
                MembershipType membershipType, double accountBalance, LocalDate creationDate) {
        super(id, role, name, surname, email, password, gender);
        this.membershipStatus = membershipStatus;
        this.membershipType = membershipType;
        this.accountBalance = accountBalance;
        this.creationDate = creationDate;
        this.boughtBooks = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
        this.returnedBooks = new ArrayList<>();
        this.historyOfUser = new ArrayList<>();
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<Book> getBoughtBooks() {
        return boughtBooks;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public List<Book> getReturnedBooks() {
        return returnedBooks;
    }

    public List<Book> getHistoryOfUser() {
        return historyOfUser;
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
                + "\nGENDER: " + getGender()
                + "\nMEMBERSHIP_STATUS: " + getMembershipStatus()
                + "\nMEMBERSHIP_TYPE: " + getMembershipType()
                + "\nBALANCE: " + getAccountBalance()
                + "\nCREATION_DATE: " + getCreationDate() + "\n";
    }
}
