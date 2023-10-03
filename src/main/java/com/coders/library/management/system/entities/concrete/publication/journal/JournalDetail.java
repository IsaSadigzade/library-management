package com.coders.library.management.system.entities.concrete.publication.journal;

import com.coders.library.management.system.enums.publication.Category;
import com.coders.library.management.system.enums.publication.Language;
import com.coders.library.management.system.enums.publication.PublicationType;
import com.coders.library.management.system.entities.concrete.publication.PublicationDetail;

import java.time.LocalDate;

public class JournalDetail extends PublicationDetail {
    public JournalDetail(PublicationType publicationType, Category category, Language language, int pageCount, String publisher, LocalDate publicationDate) {
        super(publicationType, category, language, pageCount, publisher, publicationDate);
    }
}
