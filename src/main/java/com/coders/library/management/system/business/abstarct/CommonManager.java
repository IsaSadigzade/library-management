package com.coders.library.management.system.business.abstarct;

import com.coders.library.management.system.entities.concrete.person.Person;
import com.coders.library.management.system.entities.concrete.publication.Publication;

public interface CommonManager {
    void insertPublicationsToLibrary(Publication publication, Person person);
}
