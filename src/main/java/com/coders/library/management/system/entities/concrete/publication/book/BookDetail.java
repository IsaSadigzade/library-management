package com.coders.library.management.system.entities.concrete.publication.book;

import com.coders.library.management.system.enums.publication.Category;
import com.coders.library.management.system.enums.publication.Language;
import com.coders.library.management.system.entities.concrete.publication.PublicationDetail;
import com.coders.library.management.system.enums.publication.PublicationType;

import java.time.LocalDate;

public class BookDetail extends PublicationDetail {
    public BookDetail(PublicationType publicationType, Category category, Language language, int pageCount, String publisher, LocalDate publicationDate) {
        super(publicationType, category, language, pageCount, publisher, publicationDate);
    }



}
