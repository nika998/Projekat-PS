/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.flight;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class UcitajListuLetova extends SystemOperations{
    
     public UcitajListuLetova(List<GenericEntity> letovi) {
        super();
        list = letovi;
    }

    @Override
    protected void operation() throws SOExecutionException {
        String join = "avio_linija a ON let.id_avio_linija = a.id_avio_linija";
        List<GenericEntity> letovi = database.getAll(Let.class, join, "", "");
        list = letovi;
    }
    
}
