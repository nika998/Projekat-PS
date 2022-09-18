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
public class Request implements Serializable {

    private Object parameter;
    private Operation operation;

    public Request() {
    }

    public Request(Object parameter, Operation operation) {
        this.parameter = parameter;
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

}
