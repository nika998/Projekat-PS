/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.controller;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.domain.dto.SearchDto;
import rs.bg.ac.fon.ps.domain.impl.Supervisor;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;
import rs.bg.ac.fon.ps.logic.impl.flight.IzmeniLet;
import rs.bg.ac.fon.ps.logic.impl.passenger.IzmeniPutnika;
import rs.bg.ac.fon.ps.logic.impl.flight.NadjiLet;
import rs.bg.ac.fon.ps.logic.impl.passenger.NadjiPutnika;
import rs.bg.ac.fon.ps.logic.impl.flight.ObrisiLet;
import rs.bg.ac.fon.ps.logic.impl.passenger.ObrisiPutnika;
import rs.bg.ac.fon.ps.logic.impl.flight.PretraziLetove;
import rs.bg.ac.fon.ps.logic.impl.uses.PretraziListuKoriscenjaLinija;
import rs.bg.ac.fon.ps.logic.impl.attendance.PretraziListuPrisustva;
import rs.bg.ac.fon.ps.logic.impl.passenger.PretraziPutnike;
import rs.bg.ac.fon.ps.logic.impl.lines.UcitajListuAvioLinija;
import rs.bg.ac.fon.ps.logic.impl.flight.UcitajListuLetova;
import rs.bg.ac.fon.ps.logic.impl.passenger.UcitajListuPutnika;
import rs.bg.ac.fon.ps.logic.impl.stats.UcitajStatistiku;
import rs.bg.ac.fon.ps.logic.impl.flight.ZapamtiLet;
import rs.bg.ac.fon.ps.logic.impl.attendance.ZapamtiPrisustvoLetu;
import rs.bg.ac.fon.ps.logic.impl.passenger.ZapamtiPutnika;
import rs.bg.ac.fon.ps.logic.impl.supervisor.NadjiSupervisora;

/**
 *
 * @author nikola.dulovic
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public GenericEntity login(Supervisor supervisor) throws SOExecutionException {
        SystemOperations so = new NadjiSupervisora(supervisor);
        so.execute();

        return so.getGenericEntity();
    }

    public List<GenericEntity> getLinesList(List<GenericEntity> lines) throws SOExecutionException {
        SystemOperations so = new UcitajListuAvioLinija(lines);
        so.execute();

        return so.getList();
    }

    public List<GenericEntity> getPassengersList(List<GenericEntity> passengers) throws SOExecutionException {
        SystemOperations so = new UcitajListuPutnika(passengers);
        so.execute();

        return so.getList();
    }

    public List<GenericEntity> getFlightsList(List<GenericEntity> flights) throws SOExecutionException {
        SystemOperations so = new UcitajListuLetova(flights);
        so.execute();

        return so.getList();
    }

    public GenericEntity savePassenger(Putnik passenger) throws SOExecutionException {
        SystemOperations so = new ZapamtiPutnika(passenger);
        so.execute();

        return so.getGenericEntity();
    }

    public GenericEntity saveFlight(Let flight) throws SOExecutionException {
        SystemOperations so = new ZapamtiLet(flight);
        so.execute();

        return so.getGenericEntity();
    }

    public GenericEntity saveAttendance(PrisustvoLetu attendance) throws SOExecutionException {
        SystemOperations so = new ZapamtiPrisustvoLetu(attendance);
        so.execute();

        return so.getGenericEntity();
    }

    public GenericEntity findFlights(Let flight) throws SOExecutionException {
        SystemOperations so = new NadjiLet(flight);
        so.execute();

        return so.getGenericEntity();
    }

    public GenericEntity findPassenger(Putnik passenger) throws SOExecutionException {
        SystemOperations so = new NadjiPutnika(passenger);
        so.execute();

        return so.getGenericEntity();
    }

    public GenericEntity editPassenger(Putnik passenger) throws SOExecutionException {
        SystemOperations so = new IzmeniPutnika(passenger);
        so.execute();

        return so.getGenericEntity();
    }

    public GenericEntity editFlight(Let flight) throws SOExecutionException {
        SystemOperations so = new IzmeniLet(flight);
        so.execute();

        return so.getGenericEntity();
    }

    public List<GenericEntity> searchFlights(SearchDto search) throws SOExecutionException {
        SystemOperations so = new PretraziLetove(search);
        so.execute();

        return so.getList();
    }

    public List<GenericEntity> searchPassengers(SearchDto search) throws SOExecutionException {
        SystemOperations so = new PretraziPutnike(search);
        so.execute();

        return so.getList();
    }

    public List<GenericEntity> searchAttendances(String criteria, List<GenericEntity> list) throws SOExecutionException {
        SystemOperations so = new PretraziListuPrisustva(criteria, list);
        so.execute();

        return so.getList();
    }

    public List<GenericEntity> searchLines(String criteria, List<GenericEntity> list) throws SOExecutionException {
        SystemOperations so = new PretraziListuKoriscenjaLinija(criteria, list);
        so.execute();

        return so.getList();
    }

    public Boolean deleteFlight(Let flight) throws SOExecutionException {
        SystemOperations so = new ObrisiLet(flight);
        so.execute();

        return so.isRemoved();
    }

    public Boolean deletePassenger(Putnik passenger) throws SOExecutionException {
        SystemOperations so = new ObrisiPutnika(passenger);
        so.execute();

        return so.isRemoved();
    }

    public GenericEntity getStatistics() throws SOExecutionException {
        SystemOperations so = new UcitajStatistiku();
        so.execute();

        return so.getGenericEntity();
    }

}
