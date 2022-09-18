/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.domain.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.exception.DBException;

/**
 *
 * @author nikola.dulovic
 */
public class Let implements GenericEntity {

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private String idFlight;
    private Date startTime;
    private Date endTime;
    private String company;
    private AvioLinija line;

    public Let() {
    }

    public Let(String idFlight, Date startTime, Date endTime, String company, AvioLinija line) {
        this.idFlight = idFlight;
        this.startTime = startTime;
        this.endTime = endTime;
        this.company = company;
        this.line = line;
    }

    public String getIdFlight() {
        return idFlight;
    }

    public void setId(String id) {
        this.idFlight = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public AvioLinija getLine() {
        return line;
    }

    public void setLine(AvioLinija line) {
        this.line = line;
    }

    @Override
    public String getTableName() {
        return "let";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setObjectId(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id_let,vreme_polaska,vreme_dolaska,kompanija,id_avio_linija";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getIdFlight() + "','" + new SimpleDateFormat(TIME_FORMAT).format(startTime) + "', '" + new SimpleDateFormat(TIME_FORMAT).format(endTime) + "' , '" + company + "' , " + line.getId();
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdName() {
        return "id_let";
    }

    @Override
    public LinkedList<GenericEntity> getListFromRs(ResultSet rs) throws DBException {
        LinkedList<GenericEntity> list = new LinkedList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("id_let");
                Date vremePolaska = new java.util.Date(rs.getTimestamp("vreme_polaska").getTime());
                Date vremeDolaska = new java.util.Date(rs.getTimestamp("vreme_dolaska").getTime());
                String kompanija = rs.getString("kompanija");
                AvioLinija avioLinija = new AvioLinija(rs.getLong("id_avio_linija"), rs.getString("polazno_mesto"), rs.getString("destinacija"));

                Let l = new Let(id, vremePolaska, vremeDolaska, kompanija, avioLinija);
                list.add(l);
            }
            return list;
        } catch (SQLException ex) {
            throw new DBException("Greska prilikom ucitavanja podataka");
        }

    }

    @Override
    public String setQueryForUpdate() {
        return "UPDATE let SET kompanija = '" + company
                + "', vreme_polaska = '" + new SimpleDateFormat(TIME_FORMAT).format(startTime) + "', vreme_dolaska = '" + new SimpleDateFormat(TIME_FORMAT).format(endTime)
                + "', id_avio_linija = " + getLine().getId()
                + " WHERE id_let = '" + getIdFlight() + "'";
    }

    @Override
    public String prepareQueryForSelect() {
        return "SELECT * FROM `let` let";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idFlight);
        return hash;
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
        final Let other = (Let) obj;
        if (!Objects.equals(this.idFlight, other.idFlight)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Let: " + idFlight;
    }

}
