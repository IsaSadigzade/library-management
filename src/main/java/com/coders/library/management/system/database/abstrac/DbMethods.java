package com.coders.library.management.system.database.abstrac;

import java.sql.SQLException;
import java.util.List;

public interface DbMethods<Type> {
    void createMethod() throws SQLException;
    void insertMethod(Type type) throws SQLException;
    void updateMethod(Type type) throws SQLException;
    void deleteMethod(Type type) throws SQLException;
    List<Type> getTableMethod() throws SQLException;
    List<Type> searchByParam(String paramName, String paramValue) throws SQLException;
    int getLastIdNumber();
}
