/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.exception;

/**
 *
 * @author nikola.dulovic
 */
public class AttendanceDuplicationException extends SOExecutionException{

    public AttendanceDuplicationException() {
        super("Prisustvo putnika za ovaj let je već uneto!");
    }
    
    
    
}
