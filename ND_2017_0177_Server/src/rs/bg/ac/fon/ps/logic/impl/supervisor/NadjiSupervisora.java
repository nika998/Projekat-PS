/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.supervisor;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Supervisor;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.exception.UserNotFoundException;
import rs.bg.ac.fon.ps.exception.WrongPasswordException;
import rs.bg.ac.fon.ps.logic.SystemOperations;
import rs.bg.ac.fon.ps.validator.impl.LoginValidator;

/**
 *
 * @author nikola.dulovic
 */
public class NadjiSupervisora extends SystemOperations {

    public NadjiSupervisora(Supervisor supervisor) {
        super();
        genericEntity = supervisor;
        validator = new LoginValidator();
    }

    @Override
    protected void operation() throws SOExecutionException {
        Supervisor s = (Supervisor) genericEntity;
        List<GenericEntity> supervisors = database.getAll(Supervisor.class, "", "username = '" + s.getUsername() + "' AND password != '" + s.getPassword() + "'", "");
        if (!supervisors.isEmpty()) {
            throw new WrongPasswordException();
        }
        supervisors = database.getAll(Supervisor.class, "", "username = '" + s.getUsername() + "' AND password = '" + s.getPassword() + "'", "");
        if (supervisors.isEmpty()) {
            throw new UserNotFoundException();
        }
    }

}
