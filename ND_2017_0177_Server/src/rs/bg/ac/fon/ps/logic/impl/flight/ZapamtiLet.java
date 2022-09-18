/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.flight;

import java.util.LinkedList;
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
public class ZapamtiLet extends SystemOperations {

    public ZapamtiLet(Let let) {
        super();
        genericEntity = let;
        validator = new LetValidator();
    }

    @Override
    protected void operation() throws SOExecutionException {
        LinkedList<GenericEntity> letovi = null;
        SystemOperations so = new UcitajListuLetova(letovi);
        so.execute();
        letovi = (LinkedList<GenericEntity>) so.getList();

        for (GenericEntity entity : letovi) {
            Let letFromDB = (Let) entity;
            Let letToInsert = (Let) genericEntity;

            if (letToInsert.equals(letFromDB)) {
                throw new SOExecutionException("Postoji let sa zadatom šifrom leta!");
            }

            if (letToInsert.getStartTime().equals(letFromDB.getStartTime())) {
                throw new DepartureTimeException();
            }
        }

        genericEntity = database.insert(genericEntity);
    }

}
