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
public class PrisustvoLetu implements GenericEntity {

    private Long idAttendance;
    private Putnik passenger;
    private Let flight;
    private Date boardingTime;

    public PrisustvoLetu() {
    }

    public PrisustvoLetu(Long idAttendance, Putnik passenger, Let flight, Date boardingTime) {
        this.idAttendance = idAttendance;
        this.passenger = passenger;
        this.flight = flight;
        this.boardingTime = boardingTime;
    }

    public void setId(Long idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Putnik getPassenger() {
        return passenger;
    }

    public void setPassenger(Putnik passenger) {
        this.passenger = passenger;
    }

    public Let getFlight() {
        return flight;
    }

    public void setFlight(Let flight) {
        this.flight = flight;
    }

    public Date getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(Date boardingTime) {
        this.boardingTime = boardingTime;
    }

    @Override
    public String getTableName() {
        return "prisustvo_letu";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setObjectId(long aLong) {
        idAttendance = aLong;
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id_putnik, id_let, vreme_ukrcavanja";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return passenger.getId() + ", '" + flight.getIdFlight() + "' , '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(boardingTime) + "'";
    }

    @Override
    public Long getId() {
        return idAttendance;
    }

    @Override
    public String getIdName() {
        return "id_prisustvo_letu";
    }

    @Override
    public LinkedList<GenericEntity> getListFromRs(ResultSet rs) throws DBException {
        LinkedList<GenericEntity> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Long id = rs.getLong("id_prisustvo_letu");

                Putnik putnik = new Putnik(rs.getLong("id_putnik"), rs.getString("jmbg"), rs.getString("ime"), rs.getString("prezime"), rs.getString("pol"));
                Let let
                        = new Let(rs.getString("id_let"), new java.util.Date(rs.getTimestamp("vreme_polaska").getTime()), new java.util.Date(rs.getTimestamp("vreme_dolaska").getTime()), rs.getString("kompanija"), new AvioLinija(rs.getLong("id_avio_linija"), rs.getString("polazno_mesto"), rs.getString("destinacija")));
                Date vremeUkrcavanja = new java.util.Date(rs.getTimestamp("vreme_ukrcavanja").getTime());

                PrisustvoLetu pl = new PrisustvoLetu(id, putnik, let, vremeUkrcavanja);
                list.add(pl);
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
        return "SELECT * FROM `prisustvo_letu` prisustvo_letu";
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
        final PrisustvoLetu other = (PrisustvoLetu) obj;
        if (!Objects.equals(this.passenger, other.passenger)) {
            return false;
        }
        if (!Objects.equals(this.flight, other.flight)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.passenger);
        hash = 47 * hash + Objects.hashCode(this.flight);
        return hash;
    }

}
