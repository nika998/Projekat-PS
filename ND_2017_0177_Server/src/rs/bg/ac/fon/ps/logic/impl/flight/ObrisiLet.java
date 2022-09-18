/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.flight;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class ObrisiLet extends SystemOperations {

    public ObrisiLet(Let let) {
        super();
        genericEntity = let;
    }

    @Override
    protected void operation() throws SOExecutionException {
        Let let = (Let) genericEntity;

        String join = "let l ON prisustvo_letu.id_let = l.id_let JOIN putnik p ON prisustvo_letu.id_putnik = p.id_putnik JOIN avio_linija a ON l.id_avio_linija = a.id_avio_linija";
        List<GenericEntity> prisustvaLetu = database.getAll(PrisustvoLetu.class, join, "l.id_let= '" + let.getIdFlight() + "'", "");
        prisustvaLetu.forEach((entity) -> {
            database.remove(entity);
        });
        removed = database.remove(genericEntity);
    }

}
