package com.coders.library.management.system.business.abstarct;

import com.coders.library.management.system.entities.concrete.publication.Publication;

import java.util.List;

public interface PublicationManager extends UserInputs {
    void addPublication(Publication publication);

    void updatePublication(Publication publication);

    void deletePublication(int publicationId);

    void getAllPublications();
//    List<Publication> getAllPublications();
}
