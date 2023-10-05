package com.coders.library.management.system.business.abstarct;

import com.coders.library.management.system.entities.concrete.publication.Publication;

public interface PublicationManager extends UserInputs {
    void addPublication(Publication publication);

    void updatePublication(Publication publication);

    void deletePublication(Publication publication);

    void getAllPublications();
//    List<Publication> getAllPublications();
}
