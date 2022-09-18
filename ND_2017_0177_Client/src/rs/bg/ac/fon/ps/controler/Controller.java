/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.controler;

import java.util.Date;
import java.util.List;
import rs.bg.ac.fon.ps.communication.CommunicationWithServer;
import rs.bg.ac.fon.ps.communication.Operation;
import rs.bg.ac.fon.ps.communication.Request;
import rs.bg.ac.fon.ps.communication.Response;
import rs.bg.ac.fon.ps.domain.impl.AvioLinija;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.domain.dto.SearchDto;

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

    public List<Let> getFlightList() throws Exception {
        Request req = new Request();
        req.setOperation(Operation.GET_ALL_FLIGHTS);

        CommunicationWithServer.getInstance().sendRequest(req);
        Response res = CommunicationWithServer.getInstance().getResponse();

        return (List<Let>) res.getResponseObj();
    }

    public List<AvioLinija> getLinesList() throws Exception {
        Request req = new Request();
        req.setOperation(Operation.GET_ALL_AIRLINES);

        CommunicationWithServer.getInstance().sendRequest(req);
        Response res = CommunicationWithServer.getInstance().getResponse();

        return (List<AvioLinija>) res.getResponseObj();
    }

    public Response savePassenger(Putnik passenger) throws Exception {
        Request req = new Request();

        req.setOperation(Operation.ADD_PASSENGER);
        req.setParameter(passenger);

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response saveFlight(Let flight) throws Exception {
        Request req = new Request();

        req.setOperation(Operation.ADD_FLIGHT);
        req.setParameter(flight);

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response saveFlightAttendance(PrisustvoLetu attendance) throws Exception {
        Request req = new Request();

        req.setOperation(Operation.ADD_FLIGHT_ATTENDANCE);
        req.setParameter(attendance);

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response editPassenger(Putnik passenger) throws Exception {
        Request req = new Request();

        req.setOperation(Operation.UPDATE_PASSENGER);
        req.setParameter(passenger);

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response editFlight(Let flight) throws Exception {
        Request req = new Request();

        req.setOperation(Operation.UPDATE_FLIGHT);
        req.setParameter(flight);

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response deletePassenger(Putnik passenger) throws Exception {
        Request req = new Request();

        req.setOperation(Operation.DELETE_PASSENGER);
        req.setParameter(passenger);

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response deleteFlight(Let flight) throws Exception {
        Request req = new Request();

        req.setOperation(Operation.DELETE_FLIGHT);
        req.setParameter(flight);

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response searchPassenger(String searchInput, List<GenericEntity> passengers) throws Exception {
        Request req = new Request();
        req.setOperation(Operation.SEARCH_PASSENGER);
        req.setParameter(new SearchDto(searchInput, passengers));

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response searchFlightsByLines(String searchInput, List<GenericEntity> lines) throws Exception {
        Request req = new Request();
        req.setOperation(Operation.SEARCH_FLIGHTS);
        req.setParameter(new SearchDto(searchInput, lines));

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response searchFlightsByStartDate(Date dateFrom, Date dateTo, List<GenericEntity> lines) throws Exception {
        Request req = new Request();
        req.setOperation(Operation.SEARCH_FLIGHTS);
        req.setParameter(new SearchDto(dateFrom, dateTo, lines));

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response searchUsedLines(String searchInput, List<GenericEntity> usedLines) throws Exception {
        Request req = new Request();
        req.setOperation(Operation.SEARCH_USED_LINES);
        req.setParameter(new SearchDto(searchInput, usedLines));

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response searchFlightAttendances(String searchInput, List<GenericEntity> attendances) throws Exception {
        Request req = new Request();
        req.setOperation(Operation.SEARCH_FLIGHT_ATTENDANCES);
        req.setParameter(new SearchDto(searchInput, attendances));

        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

    public Response getStatistics() throws Exception {
        Request req = new Request();
        req.setOperation(Operation.GET_STATISTICS);
        CommunicationWithServer.getInstance().sendRequest(req);

        return CommunicationWithServer.getInstance().getResponse();
    }

}
