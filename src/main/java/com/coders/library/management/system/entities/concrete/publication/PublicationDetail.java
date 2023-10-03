package com.coders.library.management.system.entities.concrete.publication;

import com.coders.library.management.system.enums.publication.Category;
import com.coders.library.management.system.enums.publication.Language;
import com.coders.library.management.system.enums.publication.PublicationType;

import java.time.LocalDate;

public class PublicationDetail {
    private PublicationType publicationType;
    private Category category;
    private Language language;
    private int pageCount;
    private String publisher;
    private LocalDate publicationDate;

    public PublicationDetail(PublicationType publicationType, Category category, Language language, int pageCount, String publisher, LocalDate publicationDate) {
        this.publicationType = publicationType;
        this.category = category;
        this.language = language;
        this.pageCount = pageCount;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public Category getCategory() {
        return category;
    }

    public Language getLanguage() {
        return language;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
