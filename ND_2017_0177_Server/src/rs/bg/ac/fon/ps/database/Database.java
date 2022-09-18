/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import rs.bg.ac.fon.ps.domain.impl.AvioLinija;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.dto.StatisticsDto;
import rs.bg.ac.fon.ps.domain.impl.KoriscenjeLinija;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.domain.impl.Supervisor;
import rs.bg.ac.fon.ps.exception.DBException;
import rs.bg.ac.fon.ps.exception.SOExecutionException;

/**
 *
 * @author nikola.dulovic
 */
public class Database {

    private Connection connection;
    private static final String CONFIG_PATH = "src\\rs\\bg\\ac\\fon\\ps\\database\\config.txt";

    public void connect() throws SOExecutionException {
        Properties properties = new Properties();
        String serverConfigFilePath = new File(CONFIG_PATH).getAbsolutePath();
        try (FileInputStream fis = new FileInputStream(serverConfigFilePath)) {
            if (connection == null || connection.isClosed()) {
                properties.load(fis);
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }
        } catch (Exception ex) {
            throw new DBException("Greška prilikom konekcije sa bazom!\n" + ex.getMessage());
        }

    }

    public void disconnect() throws SOExecutionException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new DBException("Greška prilikom raskidanja konekcije sa bazom!\n" + ex.getMessage());
            }
        }
    }

    public void commit() throws SOExecutionException {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                throw new DBException("Greška prilikom potvrđivanja transakcije!\n" + ex.getMessage());
            }
        }
    }

    public void rollback() throws SOExecutionException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DBException("Greška prilikom poništavanja transakcije!\n" + ex.getMessage());
            }
        }
    }

    public List<GenericEntity> getAll(Class entityClass, String join, String where, String orderBy) throws SOExecutionException {

        GenericEntity entity;

        try (Statement statement = connection.createStatement()) {

            if (entityClass == Supervisor.class) {
                entity = new Supervisor();
            } else if (entityClass == Putnik.class) {
                entity = new Putnik();
            } else if (entityClass == AvioLinija.class) {
                entity = new AvioLinija();
            } else if (entityClass == Let.class) {
                entity = new Let();
            } else if (entityClass == PrisustvoLetu.class) {
                entity = new PrisustvoLetu();
            } else if (entityClass == KoriscenjeLinija.class) {
                entity = new KoriscenjeLinija();
            } else if (entityClass == StatisticsDto.class) {
                entity = new StatisticsDto();
            } else {
                throw new DBException("Greška prilikom određivanja domenskog objekta!\n");
            }

            String query = entity.prepareQueryForSelect();

            if (!join.equals("")) {
                query += " JOIN " + join;
            }

            if (!where.equals("")) {
                query += " WHERE " + where;
            }

            if (!orderBy.equals("")) {
                query += " ORDER BY " + orderBy;
            }

            System.out.println(query);

            ResultSet rs = statement.executeQuery(query);

            return entity.getListFromRs(rs);

        } catch (SQLException ex) {
            throw new DBException("Greška prilikom pribavljanja podataka iz baze\n" + ex.getMessage());
        }

    }

    public GenericEntity insert(GenericEntity genericEntity) throws SOExecutionException {
        try {

            String query = "INSERT INTO " + genericEntity.getTableName() + " (" + genericEntity.getAttributeNamesForInsert() + ") VALUES (" + genericEntity.getAttributeValuesForInsert() + ")";
            System.out.println(query);
            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

                if (genericEntity.isAutoincrement()) {
                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()) {
                        genericEntity.setObjectId(rs.getLong(1));
                    }
                }
            }

        } catch (SQLException ex) {
            throw new DBException(ex.getLocalizedMessage() + "Greška prilikom kreiranja " + genericEntity.getTableName() + " u bazi!\n");
        }

        return genericEntity;
    }

    public GenericEntity update(GenericEntity genericEntity) throws SOExecutionException {
        try {
            String query = genericEntity.setQueryForUpdate();

            System.out.println(query);

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
                System.out.println("Uspesno izmenjen " + genericEntity.getTableName() + " u bazi!");
                return genericEntity;
            }

        } catch (SQLException ex) {
            throw new DBException(ex.getLocalizedMessage() + "Greška prilikom kreiranja " + genericEntity.getTableName() + " u bazi!\n");
        }
    }

    public boolean remove(GenericEntity genericEntity) {
        try {
            String query;
            if (genericEntity.getClass() == Let.class) {
                Let flightToDelete = (Let) genericEntity;
                query = "DELETE FROM " + flightToDelete.getTableName() + " WHERE " + flightToDelete.getIdName() + "= '" + flightToDelete.getIdFlight() + "'";
            } else {
                query = "DELETE FROM " + genericEntity.getTableName() + " WHERE " + genericEntity.getIdName() + "= " + genericEntity.getId();
            }
            System.out.println(query);

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
                System.out.println("Uspešno obrisan " + genericEntity.getTableName() + " iz baze!");
                return true;
            }

        } catch (SQLException ex) {
            return false;
        }
    }

}
