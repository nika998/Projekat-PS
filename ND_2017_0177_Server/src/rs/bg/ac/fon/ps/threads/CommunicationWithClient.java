/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.bg.ac.fon.ps.communication.Request;
import rs.bg.ac.fon.ps.communication.Response;
import rs.bg.ac.fon.ps.controller.Controller;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.domain.impl.Supervisor;
import rs.bg.ac.fon.ps.domain.dto.SearchDto;
import rs.bg.ac.fon.ps.forms.ServerForm;

/**
 *
 * @author nikola.dulovic
 */
public class CommunicationWithClient extends Thread {

    Socket socket;
    ServerForm serverForm;

    CommunicationWithClient(Socket socket, ServerForm serverForm) {
        this.socket = socket;
        this.serverForm = serverForm;
    }

    @Override
    public void run() {
        while (true) {
            Request req = getRequest();
            Response res = new Response();

            if (req != null) {
                switch (req.getOperation()) {
                    case LOGIN:
                        res = login(req, res);
                        break;
                    case GET_ALL_AIRLINES:
                        res = getLinesList(res);
                        break;
                    case SEARCH_FLIGHT_ATTENDANCES:
                        res = searchAttendancesList(req, res);
                        break;
                    case SEARCH_USED_LINES:
                        res = searchLinesList(req, res);
                        break;
                    case GET_ALL_PASSENGERS:
                        res = getPassengersList(res);
                        break;
                    case GET_ALL_FLIGHTS:
                        res = getFlightsList(res);
                        break;
                    case GET_PASSENGER:
                        res = findPassenger(req, res);
                        break;
                    case GET_FLIGHT:
                        res = findFlight(req, res);
                        break;
                    case ADD_PASSENGER:
                        res = savePassenger(req, res);
                        break;
                    case ADD_FLIGHT:
                        res = saveFlight(req, res);
                        break;
                    case ADD_FLIGHT_ATTENDANCE:
                        res = saveFlightAttendance(req, res);
                        break;
                    case UPDATE_PASSENGER:
                        res = editPassenger(req, res);
                        break;
                    case UPDATE_FLIGHT:
                        res = editFlight(req, res);
                        break;
                    case DELETE_FLIGHT:
                        res = deleteFlight(req, res);
                        break;
                    case DELETE_PASSENGER:
                        res = deletePassenger(req, res);
                        break;
                    case SEARCH_FLIGHTS:
                        res = searchFlights(req, res);
                        break;
                    case SEARCH_PASSENGER:
                        res = searchPassengers(req, res);
                        break;
                    case GET_STATISTICS:
                        res = getStatistics(res);
                        break;
                }
                sendResponse(res);
            }

        }
    }

    public void sendResponse(Response res) {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(res);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Request getRequest() {
        ObjectInputStream ois;
        Request req = new Request();

        try {
            ois = new ObjectInputStream(socket.getInputStream());
            req = (Request) ois.readObject();
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return req;
    }

    public Response login(Request req, Response res) {
        try {
            Supervisor supervisor = (Supervisor) req.getParameter();
            Supervisor foundSupervisor = (Supervisor) Controller.getInstance().login(supervisor);
            res.setResponseObj(foundSupervisor);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response getLinesList(Response res) {

        try {
            List<GenericEntity> lines = null;
            lines = Controller.getInstance().getLinesList(lines);
            res.setResponseObj(lines);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response getPassengersList(Response res) {
        try {
            List<GenericEntity> passengers = null;
            passengers = Controller.getInstance().getPassengersList(passengers);
            res.setResponseObj(passengers);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response getFlightsList(Response res) {
        try {
            List<GenericEntity> flights = null;
            flights = Controller.getInstance().getFlightsList(flights);
            res.setResponseObj(flights);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response findPassenger(Request req, Response res) {
        try {
            Putnik passenger = (Putnik) req.getParameter();
            Putnik foundPassenger = (Putnik) Controller.getInstance().findPassenger(passenger);
            res.setResponseObj(foundPassenger);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response findFlight(Request req, Response res) {
        try {
            Let flight = (Let) req.getParameter();
            Let foundFlight = (Let) Controller.getInstance().findFlights(flight);
            res.setResponseObj(foundFlight);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response savePassenger(Request req, Response res) {
        try {
            Putnik passenger = (Putnik) req.getParameter();
            GenericEntity genericEntity = Controller.getInstance().savePassenger(passenger);

            res.setResponseObj(genericEntity);
        } catch (Exception e) {
            res.setException(e);
        }

        return res;
    }

    private Response saveFlight(Request req, Response res) {
        try {
            Let flight = (Let) req.getParameter();
            GenericEntity genericEntity = Controller.getInstance().saveFlight(flight);

            res.setResponseObj(genericEntity);
        } catch (Exception e) {
            res.setException(e);
        }

        return res;
    }

    private Response editPassenger(Request req, Response res) {
        try {
            Putnik passenger = (Putnik) req.getParameter();
            GenericEntity genericEntity = Controller.getInstance().editPassenger(passenger);

            res.setResponseObj(genericEntity);
        } catch (Exception e) {
            res.setException(e);
        }

        return res;
    }

    private Response editFlight(Request req, Response res) {
        try {
            Let flight = (Let) req.getParameter();
            GenericEntity genericEntity = Controller.getInstance().editFlight(flight);

            res.setResponseObj(genericEntity);
        } catch (Exception e) {
            res.setException(e);
        }

        return res;
    }

    private Response deleteFlight(Request req, Response res) {
        try {
            Let flight = (Let) req.getParameter();
            Boolean ret = Controller.getInstance().deleteFlight(flight);

            res.setResponseObj(ret);
        } catch (Exception e) {
            res.setException(e);

        }

        return res;
    }

    private Response deletePassenger(Request req, Response res) {
        try {
            Putnik passenger = (Putnik) req.getParameter();
            Boolean ret = Controller.getInstance().deletePassenger(passenger);

            res.setResponseObj(ret);
        } catch (Exception e) {
            res.setException(e);

        }

        return res;
    }

    private Response searchFlights(Request req, Response res) {
        try {
            List<GenericEntity> flights;
            SearchDto search = (SearchDto) req.getParameter();
            flights = Controller.getInstance().searchFlights(search);
            res.setResponseObj(flights);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response searchPassengers(Request req, Response res) {
        try {
            List<GenericEntity> passengers;
            SearchDto search = (SearchDto) req.getParameter();
            passengers = Controller.getInstance().searchPassengers(search);
            res.setResponseObj(passengers);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response searchLinesList(Request req, Response res) {
        try {
            List<GenericEntity> usedLines;
            SearchDto search = (SearchDto) req.getParameter();
            usedLines = Controller.getInstance().searchLines(search.getCriterium(), search.getList());
            res.setResponseObj(usedLines);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response searchAttendancesList(Request req, Response res) {
        try {
            List<GenericEntity> attendances;
            SearchDto search = (SearchDto) req.getParameter();
            attendances = Controller.getInstance().searchAttendances(search.getCriterium(), search.getList());
            res.setResponseObj(attendances);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationWithClient.class.getName()).log(Level.SEVERE, null, ex);
            res.setException(ex);
        }

        return res;
    }

    private Response saveFlightAttendance(Request req, Response res) {
        try {
            PrisustvoLetu attendance = (PrisustvoLetu) req.getParameter();
            GenericEntity genericEntity = Controller.getInstance().saveAttendance(attendance);

            res.setResponseObj(genericEntity);
        } catch (Exception e) {
            res.setException(e);
        }

        return res;
    }

    private Response getStatistics(Response res) {
        try {
            GenericEntity genericEntity = Controller.getInstance().getStatistics();

            res.setResponseObj(genericEntity);
        } catch (Exception e) {
            res.setException(e);
        }

        return res;
    }

}
