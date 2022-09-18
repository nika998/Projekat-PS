/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikola.dulovic
 */
public class CommunicationWithServer {

    private Socket socket;

    private static CommunicationWithServer instance;

    private CommunicationWithServer() throws Exception {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            throw new Exception("Server je ugašen.");
        }
    }

    public static CommunicationWithServer getInstance() throws Exception {
        if (instance == null) {
            instance = new CommunicationWithServer();
        }

        return instance;
    }

    public void sendRequest(Request req) throws Exception {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(req);
        } catch (IOException ex) {
            instance = null;
            throw new Exception("Server je ugašen");
        }
    }

    public Response getResponse() {
        ObjectInputStream ois;
        Response res = new Response();

        try {
            ois = new ObjectInputStream(socket.getInputStream());
            res = (Response) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CommunicationWithServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

}
