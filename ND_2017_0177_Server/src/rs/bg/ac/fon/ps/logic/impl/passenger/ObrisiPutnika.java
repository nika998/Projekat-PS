/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.passenger;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.KoriscenjeLinija;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class ObrisiPutnika extends SystemOperations {

    public ObrisiPutnika(Putnik putnik) {
        super();
        genericEntity = putnik;
    }

    @Override
    protected void operation() throws SOExecutionException {
        Putnik putnik = (Putnik) genericEntity;
        String joinPutnikAndAvioLinija = "putnik p ON koriscenje_linija.id_putnik = p.id_putnik JOIN avio_linija a ON koriscenje_linija.id_avio_linija = a.id_avio_linija";
        List<GenericEntity> koriscenjaLinija = database.getAll(KoriscenjeLinija.class, joinPutnikAndAvioLinija, "p.id_putnik=" + putnik.getId(), "");
        koriscenjaLinija.forEach((entity) -> {
            database.remove(entity);
        });
        String joinPutnikAndLet = "putnik p ON prisustvo_letu.id_putnik = p.id_putnik JOIN let l ON prisustvo_letu.id_let = l.id_let JOIN avio_linija a ON l.id_avio_linija = a.id_avio_linija";
        List<GenericEntity> prisustvaLetu = database.getAll(PrisustvoLetu.class, joinPutnikAndLet, "p.id_putnik=" + putnik.getId(), "");
        prisustvaLetu.forEach((entity) -> {
            database.remove(entity);
        });
        removed = database.remove(genericEntity);
    }

}
