/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.passenger;

import java.util.LinkedList;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;
import rs.bg.ac.fon.ps.validator.impl.PutnikValidator;

/**
 *
 * @author nikola.dulovic
 */
public class ZapamtiPutnika extends SystemOperations {

    public ZapamtiPutnika(Putnik putnik) {
        super();
        genericEntity = putnik;
        validator = new PutnikValidator();

    }

    @Override
    protected void operation() throws SOExecutionException {
        LinkedList<GenericEntity> putnici = null;
        SystemOperations so = new UcitajListuPutnika(putnici);
        so.execute();
        putnici = (LinkedList<GenericEntity>) so.getList();

        for (GenericEntity entity : putnici) {
            Putnik putnikFromDB = (Putnik) entity;
            Putnik putnikToInsert = (Putnik) genericEntity;

            if (putnikToInsert.equals(putnikFromDB)) {
                throw new SOExecutionException("Putnik sa unetim matičnim brojem vec postoji u bazi!");
            }
        }

        genericEntity = database.insert(genericEntity);
    }

}
