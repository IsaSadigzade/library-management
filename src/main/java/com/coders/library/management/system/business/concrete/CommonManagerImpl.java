package com.coders.library.management.system.business.concrete;

import com.coders.library.management.system.business.abstarct.CommonManager;
import com.coders.library.management.system.business.abstarct.PersonManager;
import com.coders.library.management.system.business.abstarct.PublicationManager;
import com.coders.library.management.system.entities.concrete.person.Person;
import com.coders.library.management.system.entities.concrete.publication.Publication;
import com.coders.library.management.system.enums.person.Role;

public class CommonManagerImpl implements CommonManager {
    private PersonManager personManager;
    private PublicationManager publicationManager;
    @Override
    public void insertPublicationsToLibrary(Publication publication, Person person) {
        if (person.getRole().equals(Role.ADMIN) || person.getRole().equals(Role.SUB_ADMIN)) {
            publicationManager.addPublication(publication);
        }
    }
}
