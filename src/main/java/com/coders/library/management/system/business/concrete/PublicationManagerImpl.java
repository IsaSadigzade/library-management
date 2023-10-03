package com.coders.library.management.system.business.concrete;

import com.coders.library.management.system.business.abstarct.PublicationManager;
import com.coders.library.management.system.database.abstrac.PublicationDao;
import com.coders.library.management.system.entities.concrete.publication.Publication;

import java.sql.SQLException;
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
        try {
            publicationDao.insertMethod(publication);
            publicationList.add(publication);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePublication(Publication publication) {
        try {
            publicationDao.updateMethod(publication);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePublication(int publicationId) {

    }

    public void getPublicationById(int publicationId) {

    }

    @Override
    public void getAllPublications() {
        try {
            publicationDao.getTableMethod();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
