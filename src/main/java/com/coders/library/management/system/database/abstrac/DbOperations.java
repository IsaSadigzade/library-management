package com.coders.library.management.system.database.abstrac;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbOperations<Type> {
    Type toResultSet(ResultSet rs) throws SQLException;

    void toPrepareStatement(Type type, String query, Integer id) throws SQLException;
}
