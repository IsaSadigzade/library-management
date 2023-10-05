package com.coders.library.management.system.database.abstrac;

import com.coders.library.management.system.entities.concrete.publication.Publication;

import java.sql.SQLException;
import java.util.List;

public interface PublicationDao extends DbOperations<Publication>{
    void createMethod();
    void save(Publication publication);
    void update(Publication publication);
    void delete(int id);
    List<Publication> getAllPublications();
    List<Publication> searchByParam(String paramName, String paramValue);
    int getLastIdNumber();
}
