/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.lines;

import java.util.List;
import rs.bg.ac.fon.ps.domain.impl.AvioLinija;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class UcitajListuAvioLinija extends SystemOperations{

    public UcitajListuAvioLinija(List<GenericEntity> avioLinije) {
        super();
        list = avioLinije;
    } 

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> avioLinije = database.getAll(AvioLinija.class, "", "", "");
        list = avioLinije;
    }
    
}
