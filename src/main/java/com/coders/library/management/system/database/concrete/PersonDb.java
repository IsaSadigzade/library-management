package com.coders.library.management.system.database.concrete;

import com.coders.library.management.system.core.concrete.Inventory;
import com.coders.library.management.system.database.abstrac.ConnectionDb;
import com.coders.library.management.system.database.abstrac.PersonDao;
import com.coders.library.management.system.enums.person.*;
import com.coders.library.management.system.entities.concrete.person.Person;
import com.coders.library.management.system.entities.concrete.person.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDb implements PersonDao {
//    private Inventory inventory;
    List<Person> result;

    public PersonDb() {

    }
//    public PersonDb(Inventory inventory) {
//        this.inventory = inventory;
//    }

    @Override
    public List<Person> searchByParam(String paramName, String paramValue) throws SQLException {
        result = new ArrayList<>();
        String searchQuery = "SELECT * FROM PERSONS WHERE " + paramName + "=?";
        try (PreparedStatement preparedStatement = ConnectionDb.connectDb().prepareStatement(searchQuery)) {
            if (String.valueOf(paramName).equals("BALANCE")) {
                preparedStatement.setDouble(1, Double.parseDouble(paramValue));
            } else if (String.valueOf(paramName).equals("CREATION_DATE")) {
                preparedStatement.setDate(1, Date.valueOf(paramValue));
            } else {
                preparedStatement.setString(1, paramValue);
            }
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Person person = toResultSet(rs);
                    result.add(person);
//                    System.out.println(person);
//                    System.out.println();
                }
            }
        }
        return result;
    }

    @Override
    public int getLastIdNumber() {
        int lastPersonId = 0;
        String query = "SELECT MAX(id) FROM PERSONS";
        try (PreparedStatement preparedStatement = connectDb().prepareStatement(query)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    lastPersonId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastPersonId;
    }

    @Override
    public void createMethod() {
        Connection connection = connectDb();
        String createTableQuery = "CREATE TABLE IF NOT EXISTS PERSONS (" +
                "ID                     SERIAL          PRIMARY KEY," +
                "ROLE                   VARCHAR(10)     NOT NULL," +
                "NAME                   VARCHAR(255)    NOT NULL," +
                "SURNAME                VARCHAR(255)    NOT NULL," +
                "EMAIL                  VARCHAR(255)    NOT NULL UNIQUE," +
                "PASSWORD               VARCHAR(255)    NOT NULL," +
                "GENDER                 VARCHAR(10)     NOT NULL," +
                "MEMBERSHIP_STATUS      VARCHAR(20)," +
                "MEMBERSHIP_TYPE        VARCHAR(20)," +
                "BALANCE                DECIMAL(10, 2)," +
                "CREATION_DATE          DATE)";

        try (PreparedStatement preparedStatement = connectDb().prepareStatement(createTableQuery)) {
            preparedStatement.execute();
            System.out.println("TABLE PERSONS CREATED");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void save(Person person) throws SQLException {
        Connection connection = connectDb();
        String insertPersonQuery = "INSERT INTO PERSONS (ID, ROLE, NAME, SURNAME, EMAIL, PASSWORD, GENDER)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        String insertUserQuery = "INSERT INTO PERSONS (ID, ROLE, NAME, SURNAME, EMAIL, PASSWORD, GENDER, MEMBERSHIP_STATUS, MEMBERSHIP_TYPE, BALANCE, CREATION_DATE)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            if (person instanceof User) {
                toPrepareStatement(person, insertUserQuery, null);
            } else {
                toPrepareStatement(person, insertPersonQuery, null);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Person person) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public List<Person> getAllPersons() throws SQLException {
        return null;
    }

    @Override
    public void insertMethod(Person person) {
        Connection connection = connectDb();
        String insertPersonQuery = "INSERT INTO PERSONS (ID, ROLE, NAME, SURNAME, EMAIL, PASSWORD, GENDER)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        String insertUserQuery = "INSERT INTO PERSONS (ID, ROLE, NAME, SURNAME, EMAIL, PASSWORD, GENDER, MEMBERSHIP_STATUS, MEMBERSHIP_TYPE, BALANCE, CREATION_DATE)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            if (person instanceof User) {
                toPrepareStatement(person, insertUserQuery, null);
            } else {
                toPrepareStatement(person, insertPersonQuery, null);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMethod(Person person) {
        Connection connection = connectDb();
        try {
            String updateQuery;
            if (person instanceof User) {
                updateQuery = "UPDATE PERSONS SET " +
                        "ID=?, ROLE=?, NAME=?, SURNAME=?, EMAIL=?, PASSWORD=?, GENDER=?, " +
                        "MEMBERSHIP_STATUS=?, MEMBERSHIP_TYPE=?, BALANCE=?, CREATION_DATE=? " +
                        "WHERE ID=?";
            } else {
                updateQuery = "UPDATE PERSONS SET " +
                        "ID=?, ROLE=?, NAME=?, SURNAME=?, EMAIL=?, PASSWORD=?, GENDER=? " +
                        "WHERE ID=?";
            }

            toPrepareStatement(person, updateQuery, person.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void deleteMethod(Person person) {
        Connection connection = connectDb();
        String deleteQuery = "DELETE FROM PERSONS WHERE ID=?";
        try {
            toPrepareStatement(person, deleteQuery, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Person> getTableMethod() {
        Connection connection = connectDb();
        String getTableQuery = "SELECT * FROM PERSONS";
        try (PreparedStatement preparedStatement = connectDb().prepareStatement(getTableQuery)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Person person = toResultSet(rs);
                    System.out.println(person);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public void checkAndStartByEmailAndPassword(String email, String password) throws SQLException{
        String searchQuery = "SELECT * FROM PERSONS WHERE EMAIL=? AND PASSWORD=?";
        try (PreparedStatement preparedStatement = connectDb().prepareStatement(searchQuery)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Person person = toResultSet(rs);
                    System.out.println("Login is successful");
                    inventory.inventoryMenu(person);
                }
            }
        } finally {
            closeConnection(this.connectDb());
        }
    }

    @Override
    public Person toResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        Role role = Role.valueOf(rs.getString("ROLE"));
        String name = rs.getString("NAME");
        String surname = rs.getString("SURNAME");
        String email = rs.getString("EMAIL");
        String password = rs.getString("PASSWORD");
        Gender gender = Gender.valueOf(rs.getString("GENDER"));

        if (role.equals(Role.USER)) {
            MembershipStatus membershipStatus = MembershipStatus.valueOf(rs.getString("MEMBERSHIP_STATUS"));
            MembershipType membershipType = MembershipType.valueOf(rs.getString("MEMBERSHIP_TYPE"));
            double balance = rs.getDouble("BALANCE");
            LocalDate creationDate = rs.getDate("CREATION_DATE").toLocalDate();

            return new User(id, role, name, surname, email, password, gender, membershipStatus, membershipType, balance, creationDate);
        } else {
            return new Person(id, role, name, surname, email, password, gender);
        }
    }

    @Override
    public void toPrepareStatement(Person person, String query, Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = connectDb().prepareStatement(query)) {
            int index = 1;
            preparedStatement.setInt(index++, person.getId());
            preparedStatement.setString(index++, String.valueOf(person.getRole()));
            preparedStatement.setString(index++, person.getName());
            preparedStatement.setString(index++, person.getSurname());
            preparedStatement.setString(index++, person.getEmail());
            preparedStatement.setString(index++, person.getPassword());
            preparedStatement.setString(index++, String.valueOf(person.getGender()));

            if (id != null) {
                preparedStatement.setInt(index, id);
            }

            if (person instanceof User user) {
                preparedStatement.setString(index++, String.valueOf(user.getMembershipStatus()));
                preparedStatement.setString(index++, String.valueOf(user.getMembershipType()));
                preparedStatement.setDouble(index++, user.getAccountBalance());
                preparedStatement.setDate(index++, Date.valueOf(user.getCreationDate()));

                if (id != null) {
                    preparedStatement.setInt(index, user.getId());
                }
            }
            preparedStatement.execute();
        }
    }
}
