/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.domain.dto;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.exception.DBException;

/**
 *
 * @author nikola.dulovic
 */
public class StatisticsDto implements GenericEntity, Serializable {

    private int[] statistics;

    public StatisticsDto(int[] statistics) {
        this.statistics = statistics;
    }

    public StatisticsDto() {

    }

    public int[] getStatistics() {
        return statistics;
    }

    public void setStatistics(int[] statistics) {
        this.statistics = statistics;
    }

    @Override
    public String getTableName() {
        return "prisustvo_letu";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                int numPass = rs.getInt("putnici");
                int numFli = rs.getInt("letovi");
                int numLin = rs.getInt("linije");

                int[] statsArray = {numPass, numFli, numLin};

                StatisticsDto stats = new StatisticsDto(statsArray);
                list.add(stats);
            }
            return list;
        } catch (SQLException ex) {
            throw new DBException("Greska prilikom ucitavanja podataka iz baze");
        }

    }

    @Override
    public String setQueryForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareQueryForSelect() {
        return "SELECT COUNT(DISTINCT p.id_putnik) AS putnici, COUNT(DISTINCT l.id_let) AS letovi, COUNT(DISTINCT l.id_avio_linija) AS linije FROM `prisustvo_letu` prisustvo_letu";
    }

}
