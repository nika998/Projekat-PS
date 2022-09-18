/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.domain.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.exception.DBException;

/**
 *
 * @author nikola.dulovic
 */
public class AvioLinija implements GenericEntity {

    private Long idLine;
    private String start;
    private String destination;

    public AvioLinija() {
    }

    public AvioLinija(Long idLine, String start, String destination) {
        this.idLine = idLine;
        this.start = start;
        this.destination = destination;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setId(Long id) {
        this.idLine = id;
    }

    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAutoincrement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setObjectId(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAttributeNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAttributeValuesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getId() {
        return idLine;
    }

    @Override
    public String getIdName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<GenericEntity> getListFromRs(ResultSet rs) throws DBException {
        LinkedList<GenericEntity> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Long id = rs.getLong("id_avio_linija");
                String polaznoMesto = rs.getString("polazno_mesto");
                String destinacija = rs.getString("destinacija");

                AvioLinija al = new AvioLinija(id, polaznoMesto, destinacija);
                list.add(al);
            }
            return list;
        } catch (SQLException ex) {
            throw new DBException("Greska prilikom ucitavanja podataka");
        }

    }

    @Override
    public String setQueryForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareQueryForSelect() {
        return "SELECT * FROM `avio_linija` avio_linija";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AvioLinija other = (AvioLinija) obj;
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.idLine, other.idLine)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idLine);
        hash = 97 * hash + Objects.hashCode(this.start);
        hash = 97 * hash + Objects.hashCode(this.destination);
        return hash;
    }

    @Override
    public String toString() {
        return start + " - " + destination;
    }

}
