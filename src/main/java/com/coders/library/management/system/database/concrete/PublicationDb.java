package com.coders.library.management.system.database.concrete;

import com.coders.library.management.system.database.abstrac.ConnectionDb;
import com.coders.library.management.system.database.abstrac.PublicationDao;
import com.coders.library.management.system.enums.publication.Category;
import com.coders.library.management.system.enums.publication.Language;
import com.coders.library.management.system.enums.publication.PublicationType;
import com.coders.library.management.system.enums.publication.Status;
import com.coders.library.management.system.entities.concrete.publication.Publication;

import java.sql.*;
import java.util.List;

public class PublicationDb implements ConnectionDb, PublicationDao {
    @Override
    public void deleteMethod(Publication publication) {
        Connection connection = connectDb();
        String deleteQuery = "DELETE FROM PUBLICATIONS WHERE ID=?";

        try {
            toPrepareStatement(publication, deleteQuery, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void updateMethod(Publication publication) {
        Connection connection = connectDb();
        String updateQuery = "UPDATE PUBLICATIONS SET " +
                "ID=?, TYPE=?, NAME=?, AUTHOR=?, PRICE=?, RATING=?, CATEGORY=?, LANGUAGE=?, " +
                "PAGE_COUNT=?, PUBLISHER=?, PUBLICATION_DATE=?, QUANTITY=?, STATUS=?, " +
                "IS_SELLABLE=?, SOLD=?, IS_BORROWABLE=?, BORROWED=?, IS_RESERVABLE=?, RESERVED=? " +
                "WHERE ID=?";

        try {
            toPrepareStatement(publication, updateQuery, publication.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insertMethod(Publication publication) {
        Connection connection = connectDb();
        String insertQuery = "INSERT INTO PUBLICATIONS (ID, TYPE, NAME, AUTHOR, PRICE, RATING, CATEGORY, LANGUAGE, PAGE_COUNT, " +
                "PUBLISHER, PUBLICATION_DATE, QUANTITY, STATUS, IS_SELLABLE, SOLD, IS_BORROWABLE, BORROWED, IS_RESERVABLE, RESERVED)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            toPrepareStatement(publication, insertQuery, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Publication> getTableMethod() {
        Connection connection = connectDb();
        String getTableQuery = "SELECT * FROM PUBLICATIONS";
        try (PreparedStatement preparedStatement = connectDb().prepareStatement(getTableQuery)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Publication publication = toResultSet(rs);
                    System.out.println(publication);
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
    public List<Publication> searchByParam(String paramName, String paramValue) throws SQLException {
        return null;
    }

    @Override
    public int getLastIdNumber() {
        return 0;
    }

    @Override
    public void createMethod() {
        Connection connection = connectDb();
        String createTableQuery = "CREATE TABLE IF NOT EXISTS PUBLICATIONS ("
                + "ID                   SERIAL          PRIMARY KEY,"
                + "TYPE                 VARCHAR(50)     NOT NULL,"
                + "NAME                 VARCHAR(255)    NOT NULL,"
                + "AUTHOR               VARCHAR(255)    NOT NULL,"
                + "PRICE                DECIMAL(10, 2)  NOT NULL,"
                + "RATING               FLOAT           NOT NULL,"
                + "CATEGORY             VARCHAR(50)     NOT NULL,"
                + "LANGUAGE             VARCHAR(50)     NOT NULL,"
                + "PAGE_COUNT           INT             NOT NULL,"
                + "PUBLISHER            VARCHAR(255)    NOT NULL,"
                + "PUBLICATION_DATE     DATE            NOT NULL,"
                + "QUANTITY             INT             NOT NULL,"
                + "STATUS               VARCHAR(20)     NOT NULL,"
                + "IS_SELLABLE          BOOLEAN         NOT NULL,"
                + "SOLD                 INT             NOT NULL,"
                + "IS_BORROWABLE        BOOLEAN         NOT NULL,"
                + "BORROWED             INT             NOT NULL,"
                + "IS_RESERVABLE        BOOLEAN         NOT NULL,"
                + "RESERVED             INT             NOT NULL)";

        try (PreparedStatement preparedStatement = connectDb().prepareStatement(createTableQuery)) {
            preparedStatement.execute();
            System.out.println("TABLE PUBLICATIONS CREATED");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Publication toResultSet(ResultSet rs) throws SQLException {
        return new Publication(
                rs.getInt("ID"),
                PublicationType.valueOf(rs.getString("TYPE")),
                rs.getString("NAME"),
                rs.getString("AUTHOR"),
                rs.getFloat("PRICE"),
                rs.getFloat("RATING"),
                Category.valueOf(rs.getString("CATEGORY")),
                Language.valueOf(rs.getString("LANGUAGE")),
                rs.getInt("PAGE_COUNT"),
                rs.getString("PUBLISHER"),
                rs.getDate("PUBLICATION_DATE").toLocalDate(),
                rs.getInt("QUANTITY"),
                Status.valueOf(rs.getString("STATUS")),
                rs.getBoolean("IS_SELLABLE"),
                rs.getInt("SOLD"),
                rs.getBoolean("IS_BORROWABLE"),
                rs.getInt("BORROWED"),
                rs.getBoolean("IS_RESERVABLE"),
                rs.getInt("RESERVED")
        );
    }

    @Override
    public void toPrepareStatement(Publication publication, String query, Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = connectDb().prepareStatement(query)) {
            int index = 1;
            preparedStatement.setInt(index++, publication.getId());
            preparedStatement.setString(index++, String.valueOf(publication.getPublicationDetail().getPublicationType()));
            preparedStatement.setString(index++, publication.getName());
            preparedStatement.setString(index++, publication.getAuthor());
            preparedStatement.setDouble(index++, publication.getPrice());
            preparedStatement.setDouble(index++, publication.getRating());
            preparedStatement.setString(index++, String.valueOf(publication.getPublicationDetail().getCategory()));
            preparedStatement.setString(index++, String.valueOf(publication.getPublicationDetail().getLanguage()));
            preparedStatement.setInt(index++, publication.getPublicationDetail().getPageCount());
            preparedStatement.setString(index++, publication.getPublicationDetail().getPublisher());
            preparedStatement.setDate(index++, Date.valueOf(publication.getPublicationDetail().getPublicationDate()));
            preparedStatement.setInt(index++, publication.getInventoryItem().getQuantity());
            preparedStatement.setString(index++, String.valueOf(publication.getInventoryItem().getStatus()));
            preparedStatement.setBoolean(index++, publication.getInventoryItem().isSellable());
            preparedStatement.setInt(index++, publication.getInventoryItem().getSold());
            preparedStatement.setBoolean(index++, publication.getInventoryItem().isBorrowable());
            preparedStatement.setInt(index++, publication.getInventoryItem().getBorrowed());
            preparedStatement.setBoolean(index++, publication.getInventoryItem().isReservable());
            preparedStatement.setInt(index++, publication.getInventoryItem().getReserved());

            if (id != null) {
                preparedStatement.setInt(index, id);
            }

            preparedStatement.execute();
        }
    }

}
