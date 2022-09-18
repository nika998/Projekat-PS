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
public class UcitajListuPutnika extends SystemOperations {

    public UcitajListuPutnika(List<GenericEntity> putnici) {
        super();
        list = putnici;
    }

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> putnici = database.getAll(Putnik.class, "", "", "");
        list = putnici;
    }

}
