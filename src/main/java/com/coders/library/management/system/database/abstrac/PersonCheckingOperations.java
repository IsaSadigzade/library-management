package com.coders.library.management.system.database.abstrac;

import java.sql.SQLException;

public interface PersonCheckingOperations {
    void checkAndStartByEmailAndPassword(String email, String password) throws SQLException;
}
