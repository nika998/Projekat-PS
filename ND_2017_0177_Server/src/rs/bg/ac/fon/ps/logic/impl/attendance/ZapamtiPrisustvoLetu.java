/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.attendance;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.KoriscenjeLinija;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.exception.AttendanceDuplicationException;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;
import rs.bg.ac.fon.ps.logic.impl.uses.ZapamtiKoriscenjeLinije;

/**
 *
 * @author nikola.dulovic
 */
public class ZapamtiPrisustvoLetu extends SystemOperations {

    public ZapamtiPrisustvoLetu(PrisustvoLetu prisustvo) {
        super();
        genericEntity = prisustvo;

    }

    @Override
    protected void operation() throws SOExecutionException {
        List<GenericEntity> prisustva = null;
        PrisustvoLetu p = (PrisustvoLetu) genericEntity;
        SystemOperations so = new PretraziListuPrisustva(p.getPassenger().getId().toString(), prisustva);
        so.execute();
        prisustva = so.getList();

        PrisustvoLetu pl = (PrisustvoLetu) genericEntity;
        KoriscenjeLinija koriscenjeToInsert = new KoriscenjeLinija(0L, pl.getPassenger(), pl.getFlight().getLine());

        for (GenericEntity entity : prisustva) {
            PrisustvoLetu prisustvoFromDB = (PrisustvoLetu) entity;
            PrisustvoLetu prisustvoToInsert = (PrisustvoLetu) genericEntity;

            if (prisustvoToInsert.equals(prisustvoFromDB)) {
                throw new AttendanceDuplicationException();
            }
        }

        genericEntity = database.insert(genericEntity);

        so = new ZapamtiKoriscenjeLinije(koriscenjeToInsert);
        so.execute();

    }

}
