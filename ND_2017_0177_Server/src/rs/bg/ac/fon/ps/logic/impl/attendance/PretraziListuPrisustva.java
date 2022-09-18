/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.attendance;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class PretraziListuPrisustva extends SystemOperations {

    private final String searchInput;

    public PretraziListuPrisustva(String input, List<GenericEntity> prisustva) {
        super();
        list = prisustva;
        searchInput = input;
    }

    @Override
    protected void operation() throws SOExecutionException {
        String join = "let l ON prisustvo_letu.id_let = l.id_let JOIN avio_linija a ON l.id_avio_linija = a.id_avio_linija JOIN putnik p ON prisustvo_letu.id_putnik = p.id_putnik";
        List<GenericEntity> letovi = database.getAll(PrisustvoLetu.class, join, "p.id_putnik =" + searchInput, "");
        list = letovi;
    }

}
