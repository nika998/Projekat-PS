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
import rs.bg.ac.fon.ps.validator.impl.PutnikValidator;

/**
 *
 * @author nikola.dulovic
 */
public class IzmeniPutnika extends SystemOperations {

    public IzmeniPutnika(Putnik putnik) {
        super();
        genericEntity = putnik;
        validator = new PutnikValidator();
    }

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> putnici = database.getAll(Putnik.class, "", "", "");

        for (GenericEntity entity : putnici) {
            Putnik putnikFromDB = (Putnik) entity;
            Putnik putnikToEdit = (Putnik) this.genericEntity;

            if (putnikToEdit.equals(putnikFromDB)) {
                genericEntity = database.update(putnikToEdit);
                return;
            }
        }

        throw new SOExecutionException("Sistem ne moze da obradi putnika");

    }

}
