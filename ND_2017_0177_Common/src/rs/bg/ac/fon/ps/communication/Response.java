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
public class Response implements Serializable {

    private Exception exception;
    private Object responseObj;

    public Response() {
        exception = null;
    }

    public Response(Exception exception, Object responseObj) {
        this.exception = exception;
        this.responseObj = responseObj;
    }

    public Object getResponseObj() {
        return responseObj;
    }

    public void setResponseObj(Object responseObj) {
        this.responseObj = responseObj;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
