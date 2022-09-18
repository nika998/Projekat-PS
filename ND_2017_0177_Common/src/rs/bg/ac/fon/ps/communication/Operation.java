/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.communication;

import java.io.Serializable;

/**
 *
 * @author nikola.dulovic
 */
public enum Operation implements Serializable{
    
    LOGIN,
    ADD_FLIGHT,
    ADD_PASSENGER,
    ADD_FLIGHT_ATTENDANCE,
    GET_ALL_FLIGHTS,
    GET_ALL_PASSENGERS,
    GET_ALL_AIRLINES,
    GET_FLIGHT,
    GET_PASSENGER,
    UPDATE_FLIGHT,
    UPDATE_PASSENGER,
    DELETE_FLIGHT,
    DELETE_PASSENGER,  
    SEARCH_FLIGHT_ATTENDANCES,
    SEARCH_USED_LINES,
    SEARCH_PASSENGER,
    SEARCH_FLIGHTS,
    GET_STATISTICS
    
}
