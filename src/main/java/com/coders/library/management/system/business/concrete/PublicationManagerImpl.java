package com.coders.library.management.system.business.concrete;

import com.coders.library.management.system.business.abstarct.PublicationManager;
import com.coders.library.management.system.database.abstrac.PublicationDao;
import com.coders.library.management.system.entities.concrete.publication.Publication;

import java.util.ArrayList;
import java.util.List;

public class PublicationManagerImpl implements PublicationManager {
    private final PublicationDao publicationDao;
    List<Publication> publicationList = new ArrayList<>();

    public PublicationManagerImpl(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    @Override
    public void addPublication(Publication publication) {
        publicationDao.save(publication);
        publicationList.add(publication);
    }

    @Override
    public void updatePublication(Publication publication) {
        publicationDao.update(publication);
    }

    @Override
    public void deletePublication(Publication publication) {
        publicationDao.delete(publication.getId());
    }

    public void getPublicationById(int publicationId) {

    }

    @Override
    public void getAllPublications() {
        publicationDao.getAllPublications();
    }
}
