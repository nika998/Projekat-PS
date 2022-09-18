/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import rs.bg.ac.fon.ps.exception.DBException;

/**
 *
 * @author nikola.dulovic
 */
public interface GenericEntity extends Serializable {

    public String getTableName();

    public boolean isAutoincrement();

    public void setObjectId(long aLong);

    public String getAttributeNamesForInsert();

    public String getAttributeValuesForInsert();

    public Long getId();

    public String getIdName();

    public List<GenericEntity> getListFromRs(ResultSet rs) throws DBException;

    public String setQueryForUpdate();

    public String prepareQueryForSelect();

}
