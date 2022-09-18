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
public class NadjiLet extends SystemOperations {

    public NadjiLet(Let let) {
        super();
        genericEntity = let;
    }

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> letovi = database.getAll(Let.class, "", "id_let = " + genericEntity.getId(), "");
        if (letovi.isEmpty()) {
            throw new SOExecutionException("Let ne postoji u bazi");
        }
    }

}
