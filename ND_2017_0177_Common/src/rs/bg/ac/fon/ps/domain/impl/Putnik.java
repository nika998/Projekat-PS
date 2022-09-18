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
public class Putnik implements GenericEntity {

    private Long idPassenger;
    private String jmbg;
    private String name;
    private String surname;
    private String gender;

    public Putnik() {
    }

    public Putnik(Long idPassenger, String jmbg, String name, String surname, String gender) {
        this.idPassenger = idPassenger;
        this.jmbg = jmbg;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getTableName() {
        return "putnik";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setObjectId(long aLong) {
        idPassenger = aLong;
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "ime,prezime,jmbg,pol";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + name + "', '" + surname + "' , '" + jmbg + "' , '" + gender + "'";
    }

    @Override
    public Long getId() {
        return idPassenger;
    }

    @Override
    public String getIdName() {
        return "id_putnik";
    }

    @Override
    public LinkedList<GenericEntity> getListFromRs(ResultSet rs) throws DBException {
        LinkedList<GenericEntity> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Long id = rs.getLong("id_putnik");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String jmbgRes = rs.getString("jmbg");
                String pol = rs.getString("pol");

                Putnik p = new Putnik(id, jmbgRes, ime, prezime, pol);
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            throw new DBException("Greska prilikom ucitavanja podataka");
        }

    }

    @Override
    public String setQueryForUpdate() {
        return "UPDATE putnik SET ime = '" + name
                + "', prezime = '"
                + surname + "', jmbg = '" + jmbg + "', pol ='" + gender + "'"
                + " WHERE id_putnik = " + getId();
    }

    @Override
    public String prepareQueryForSelect() {
        return "SELECT * FROM `putnik` putnik";
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
        final Putnik other = (Putnik) obj;
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.jmbg);
        return hash;
    }

}
