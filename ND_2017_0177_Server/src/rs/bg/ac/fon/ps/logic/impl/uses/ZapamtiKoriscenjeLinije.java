/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.uses;

import java.util.LinkedList;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.KoriscenjeLinija;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class ZapamtiKoriscenjeLinije extends SystemOperations {

    public ZapamtiKoriscenjeLinije(KoriscenjeLinija usedLines) {
        super();
        genericEntity = usedLines;

    }

    @Override
    protected void operation() throws SOExecutionException {
        LinkedList<GenericEntity> usedLines = null;
        KoriscenjeLinija kl = (KoriscenjeLinija) genericEntity;
        SystemOperations so = new PretraziListuKoriscenjaLinija(kl.getPassenger().getId().toString(), usedLines);
        so.execute();
        usedLines = (LinkedList<GenericEntity>) so.getList();

        for (GenericEntity entity : usedLines) {
            KoriscenjeLinija usedLineFromDB = (KoriscenjeLinija) entity;
            KoriscenjeLinija usedLineToInsert = (KoriscenjeLinija) genericEntity;

            if (usedLineToInsert.equals(usedLineFromDB)) {
                return;
            }
        }

        genericEntity = database.insert(genericEntity);
    }

}
