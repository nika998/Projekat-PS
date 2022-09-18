/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.passenger;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class NadjiPutnika extends SystemOperations {

    public NadjiPutnika(Putnik putnik) {
        super();
        genericEntity = putnik;
    }

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> putnici = database.getAll(Putnik.class, "", "id_putnik = " + genericEntity.getId(), "");
        if (putnici.isEmpty()) {
            throw new SOExecutionException("Putnik ne postoji u bazi");
        }
    }

}
