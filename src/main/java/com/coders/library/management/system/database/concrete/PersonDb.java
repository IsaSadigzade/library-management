package com.coders.library.management.system.database.concrete;

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
    List<Person> result;

    @Override
    public void createMethod() {
        Connection connection = ConnectionDb.connectDb();
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

        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
            preparedStatement.execute();
            System.out.println("TABLE PERSONS CREATED");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDb.closeConnection(connection);
        }
    }

    @Override
    public void save(Person person) {
        Connection connection = ConnectionDb.connectDb();
        try {
            String insertQuery;
            if (person instanceof User) {
                insertQuery = "INSERT INTO PERSONS (ID, ROLE, NAME, SURNAME, EMAIL, PASSWORD, GENDER, MEMBERSHIP_STATUS, MEMBERSHIP_TYPE, BALANCE, CREATION_DATE)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                insertQuery = "INSERT INTO PERSONS (ID, ROLE, NAME, SURNAME, EMAIL, PASSWORD, GENDER)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
            }

            toPrepareStatement(person, insertQuery, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDb.closeConnection(connection);
        }
    }

    @Override
    public void update(Person person) {
        Connection connection = ConnectionDb.connectDb();
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
            ConnectionDb.closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = ConnectionDb.connectDb();
        String deleteQuery = "DELETE FROM PERSONS WHERE ID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("User has been deleted successfully");
            } else {
                System.out.println("User with id: " + id + " was not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while deleting the user", e);
        } finally {
            ConnectionDb.closeConnection(connection);
        }
    }


    @Override
    public List<Person> getAllPersons() {
        result = new ArrayList<>();
        Connection connection = ConnectionDb.connectDb();
        String getTableQuery = "SELECT * FROM PERSONS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getTableQuery)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Person person = toResultSet(rs);
                    result.add(person);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDb.closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Person> searchByParam(String paramName, String paramValue) {
        result = new ArrayList<>();
        Connection connection = ConnectionDb.connectDb();
        String searchQuery = "SELECT * FROM PERSONS WHERE " + paramName + "=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
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
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDb.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int getLastIdNumber() {
        Connection connection = ConnectionDb.connectDb();
        int lastPersonId = 0;
        String query = "SELECT MAX(id) FROM PERSONS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    lastPersonId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDb.closeConnection(connection);
        }
        return lastPersonId;
    }


    @Override
    public void checkAndStartByEmailAndPassword(String email, String password) {
        Connection connection = ConnectionDb.connectDb();
        String searchQuery = "SELECT * FROM PERSONS WHERE EMAIL=? AND PASSWORD=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Person person = toResultSet(rs);
                    System.out.println("Login is successful");
//                    inventory.inventoryMenu(person);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDb.closeConnection(connection);
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
        try (PreparedStatement preparedStatement = ConnectionDb.connectDb().prepareStatement(query)) {
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
