/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;

/**
 *
 * @author nikola.dulovic
 */
public class SearchDto implements Serializable {

    private String searchCriteria;
    private Date dateFrom;
    private Date dateTo;
    private List<GenericEntity> list;

    public SearchDto() {
    }

    public SearchDto(String searchCriteria, List<GenericEntity> list) {
        this.searchCriteria = searchCriteria;
        this.list = list;
    }

    public SearchDto(Date dateFrom, Date dateTo, List<GenericEntity> list) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.list = list;
    }

    public List<GenericEntity> getList() {
        return list;
    }

    public void setList(List<GenericEntity> list) {
        this.list = list;
    }

    public String getCriterium() {
        return searchCriteria;
    }

    public void setCriterium(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

}
