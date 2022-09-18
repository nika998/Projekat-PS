/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.bg.ac.fon.ps.forms.ServerForm;

/**
 *
 * @author nikola.dulovic
 */
public class StartServerThread extends Thread {

    ServerForm serverForm;
    ServerSocket serverSocket;
    ArrayList<Socket> connectedUsers;

    public StartServerThread(ServerForm sf) {
        this.serverForm = sf;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            connectedUsers = new ArrayList<>();
            serverForm.serverStarted();

            while (true) {
                Socket s = serverSocket.accept();
                connectedUsers.add(s);
                CommunicationWithClient cwc = new CommunicationWithClient(s, serverForm);
                cwc.start();
            }

        } catch (SocketException ex) {
            Logger.getLogger(StartServerThread.class.getName()).log(Level.SEVERE, "Server is closed", ex);

        } catch (IOException e) {
            Logger.getLogger(StartServerThread.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void stopServer() {
        try {
            for (Socket socket : connectedUsers) {
                socket.close();
            }
            serverSocket.close();
            serverForm.serverStopped();
        } catch (IOException ex) {
            Logger.getLogger(StartServerThread.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
