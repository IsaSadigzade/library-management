package com.coders.library.management.system.core.concrete;

import com.coders.library.management.system.business.abstarct.PersonManager;
import com.coders.library.management.system.business.abstarct.PublicationManager;
import com.coders.library.management.system.enums.publication.Category;
import com.coders.library.management.system.enums.publication.Language;
import com.coders.library.management.system.enums.publication.PublicationType;
import com.coders.library.management.system.enums.publication.Status;
import com.coders.library.management.system.enums.person.Gender;
import com.coders.library.management.system.enums.person.MembershipStatus;
import com.coders.library.management.system.enums.person.MembershipType;
import com.coders.library.management.system.enums.person.Role;
import com.coders.library.management.system.entities.concrete.person.User;
import com.coders.library.management.system.entities.concrete.publication.InventoryItem;
import com.coders.library.management.system.entities.concrete.publication.Publication;
import com.coders.library.management.system.entities.concrete.publication.book.Book;
import com.coders.library.management.system.entities.concrete.publication.book.BookDetail;
import com.coders.library.management.system.entities.concrete.publication.comic.Comic;
import com.coders.library.management.system.entities.concrete.publication.comic.ComicDetail;
import com.coders.library.management.system.entities.concrete.publication.journal.Journal;
import com.coders.library.management.system.entities.concrete.publication.journal.JournalDetail;
import com.coders.library.management.system.entities.concrete.person.Admin;
import com.coders.library.management.system.entities.concrete.person.Person;
import com.coders.library.management.system.entities.concrete.person.SubAdmin;

import java.time.LocalDate;

public class Library {
    private PublicationManager publicationManager;
    private PersonManager personManager;

//    public Library() {
//        publicationManager = new PublicationManagerImpl();
//        personManager = new PersonManagerImpl(new PersonDb(new Inventory()));
//        initPublications();
//        initPersons();
//    }

    // TODO: Publication-da kitablari Admin, Subadmin ve komekci User varsa onlar elave eleye biler
    // TODO: User sadece kitab ala, kiralaya, reserv eleye, geri qaytara biler
    // TODO: butun emeliyyatlar tarixce kimi user-da qeyd edilecek (account isleri daxil)
    // TODO: Her bir islem ucun (almaq, kiralamaq, reserv) ucun odeme isleri aparilacaq
//    void initPublications() {
//        BookDetail bDetail1 = new BookDetail(PublicationType.BOOK, Category.FICTION, Language.ENGLISH,
//                107, "Publisher", LocalDate.of(1937, 2, 26));
//        InventoryItem bItem1 = new InventoryItem(5, Status.AVAILABLE, true, 0, false, 0, true, 0);
//        Publication book1 = new Book(1, "Of Mice and Men", "John Steinberg", 9.99, 9.85, bDetail1, bItem1);
//        publicationManager.addPublication(book1, );
//
//        ComicDetail cDetail1 = new ComicDetail(PublicationType.COMIC, Category.FANTASY, Language.ENGLISH,
//                35, "Publisher", LocalDate.of(2023, 8, 22));
//        InventoryItem cItem1 = new InventoryItem(3, Status.AVAILABLE, true, 0, true, 0, true, 0);
//        Publication comic1 = new Comic(2, "Spider Man", "Stan Lee", 6.5, 8.8, cDetail1, cItem1);
//        publicationManager.addPublication(comic1, );
//
//        JournalDetail jDetail1 = new JournalDetail(PublicationType.JOURNAL, Category.SELF_HELP, Language.ENGLISH,
//                30, "Publisher", LocalDate.of(2023, 11, 16));
//        InventoryItem jItem1 = new InventoryItem(12, Status.AVAILABLE, true, 0, true, 0, true, 0);
//        Publication journal1 = new Journal(3, "Times", "Henry Luce", 3.39, 8.5, jDetail1, jItem1);
//        publicationManager.addPublication(journal1, );
//    }

    void initPersons() {
        Person admin1 = new Admin(1, Role.ADMIN, "Isa", "Sadigzade", "isa@mail.ru", "12345", Gender.MALE);
        personManager.insertPerson(admin1);

        Person subAdmin1 = new SubAdmin(2, Role.SUB_ADMIN, "Ali", "Sadigli", "ali@mail.ru", "12345", Gender.MALE);
        personManager.insertPerson(subAdmin1);

        Person user1 = new User(3, Role.USER, "Murad", "Baylar", "murad.b@mail.ru", "12345",
                Gender.MALE, MembershipStatus.ACTIVE_MEMBER, MembershipType.ADULT_MEMBERSHIP, 0, LocalDate.now());
        personManager.insertPerson(user1);

        Person user2 = new User(4, Role.USER, "Murad", "Aliyev", "murad.a@mail.ru", "12345",
                Gender.MALE, MembershipStatus.TEMPORARY_MEMBER, MembershipType.ADULT_MEMBERSHIP, 0, LocalDate.now());
        personManager.insertPerson(user2);
    }
}
