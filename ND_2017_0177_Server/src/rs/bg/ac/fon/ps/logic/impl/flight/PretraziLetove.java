/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.flight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.domain.dto.SearchDto;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class PretraziLetove extends SystemOperations {

    private final String searchInput;
    private final Date dateFrom;
    private final Date dateTo;
    private final static String TIME_FORMAT = "yyyy.MM.dd HH:mm:ss";

    public PretraziLetove(SearchDto search) {
        super();
        searchInput = search.getCriterium();
        dateFrom = search.getDateFrom();
        dateTo = search.getDateTo();
        list = search.getList();
    }

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> letovi = null;
        String join = "avio_linija a ON let.id_avio_linija = a.id_avio_linija";
        if (searchInput != null) {
            letovi = database.getAll(Let.class, join, "let.id_avio_linija = " + searchInput, "");
        } else if (dateFrom != null && dateTo != null) {
            letovi = database.getAll(Let.class, join, "let.vreme_polaska BETWEEN '" + new SimpleDateFormat(TIME_FORMAT).format(dateFrom) + "' AND '" + new SimpleDateFormat(TIME_FORMAT).format(dateTo) + "'", "");
        } else {
            throw new SOExecutionException("Nepotpuni podaci!");
        }
        list = letovi;
    }

}
