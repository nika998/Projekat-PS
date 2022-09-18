/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.flight;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.exception.DepartureTimeException;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;
import rs.bg.ac.fon.ps.validator.impl.LetValidator;

/**
 *
 * @author nikola.dulovic
 */
public class IzmeniLet extends SystemOperations {

    public IzmeniLet(Let let) {
        super();
        genericEntity = let;
        validator = new LetValidator();
    }

    @Override
    protected void operation() throws SOExecutionException {
        String join = "avio_linija a ON let.id_avio_linija = a.id_avio_linija";
        List<GenericEntity> letovi = database.getAll(Let.class, join, "", "");

        for (GenericEntity entity : letovi) {
            Let letFromDB = (Let) entity;
            Let letToEdit = (Let) this.genericEntity;

            if (!letToEdit.getIdFlight().equals(letFromDB.getIdFlight()) && letToEdit.equals(letFromDB)) {
                throw new DepartureTimeException();
            }

            if (letToEdit.getIdFlight().equals(letFromDB.getIdFlight())) {
                genericEntity = database.update(letToEdit);
                return;
            }
        }

        throw new SOExecutionException("Sistem ne moze da obradi let");

    }
}
