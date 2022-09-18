/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.uses;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.KoriscenjeLinija;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class PretraziListuKoriscenjaLinija extends SystemOperations {

    private final String searchInput;

    public PretraziListuKoriscenjaLinija(String input, List<GenericEntity> koriscenja) {
        super();
        list = koriscenja;
        searchInput = input;
    }

    @Override
    protected void operation() throws SOExecutionException {
        String join = "avio_linija a ON koriscenje_linija.id_avio_linija = a.id_avio_linija JOIN putnik p ON koriscenje_linija.id_putnik = p.id_putnik";
        List<GenericEntity> koriscenja = database.getAll(KoriscenjeLinija.class, join, "p.id_putnik = " + searchInput, "");
        list = koriscenja;
    }

}
