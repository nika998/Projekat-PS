/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.passenger;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.domain.dto.SearchDto;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class PretraziPutnike extends SystemOperations {

    private final String searchInput;

    public PretraziPutnike(SearchDto search) {
        super();
        this.searchInput = search.getCriterium();
        list = search.getList();
    }

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> putnici = database.getAll(Putnik.class, "", "jmbg LIKE '%" + searchInput + "%' OR ime LIKE '%" + searchInput + "%' OR prezime LIKE '%" + searchInput + "%' OR pol LIKE '%" + searchInput + "%'", "");
        list = putnici;

    }

}
