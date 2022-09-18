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
public class KoriscenjeLinija implements GenericEntity {

    private Long idUsedLines;
    private Putnik passenger;
    private AvioLinija line;

    public KoriscenjeLinija() {
    }

    public KoriscenjeLinija(Long idUsedLines, Putnik passenger, AvioLinija line) {
        this.idUsedLines = idUsedLines;
        this.passenger = passenger;
        this.line = line;
    }

    public void setId(Long idUsedLines) {
        this.idUsedLines = idUsedLines;
    }

    public Putnik getPassenger() {
        return passenger;
    }

    public void setPassenger(Putnik passenger) {
        this.passenger = passenger;
    }

    public AvioLinija getLine() {
        return line;
    }

    public void setLine(AvioLinija line) {
        this.line = line;
    }

    @Override
    public String getTableName() {
        return "koriscenje_linija";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setObjectId(long aLong) {
        idUsedLines = aLong;
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id_putnik,id_avio_linija";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return passenger.getId() + ", " + line.getId();
    }

    @Override
    public Long getId() {
        return idUsedLines;
    }

    @Override
    public String getIdName() {
        return "id_koriscenje_linija";
    }

    @Override
    public LinkedList<GenericEntity> getListFromRs(ResultSet rs) throws DBException {
        LinkedList<GenericEntity> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Long id = rs.getLong("id_koriscenje_linija");

                Putnik putnik = new Putnik(rs.getLong("id_putnik"), rs.getString("jmbg"), rs.getString("ime"), rs.getString("prezime"), rs.getString("pol"));
                AvioLinija avioLinija = new AvioLinija(rs.getLong("id_avio_linija"), rs.getString("polazno_mesto"), rs.getString("destinacija"));

                KoriscenjeLinija kl = new KoriscenjeLinija(id, putnik, avioLinija);
                list.add(kl);
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
        return "SELECT * FROM `koriscenje_linija` koriscenje_linija";
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
        final KoriscenjeLinija other = (KoriscenjeLinija) obj;
        if (!Objects.equals(this.passenger, other.passenger)) {
            return false;
        }
        if (!Objects.equals(this.line, other.line)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.passenger);
        hash = 97 * hash + Objects.hashCode(this.line);
        return hash;
    }

}
