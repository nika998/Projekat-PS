/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.validator.impl;

import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Supervisor;
import rs.bg.ac.fon.ps.exception.ValidationException;
import rs.bg.ac.fon.ps.validator.Validator;

/**
 *
 * @author nikola.dulovic
 */
public class LoginValidator implements Validator {

    @Override
    public void validate(GenericEntity genericEntity) throws ValidationException {
        try {
            Supervisor supervisor = (Supervisor) genericEntity;
            String errors = "";

            if (supervisor.getUsername().isEmpty() && supervisor.getPassword().isEmpty()) {
                errors += "Morate uneti korisničko ime i šifru!\n";
            } else if (supervisor.getUsername().isEmpty()) {
                errors += "Morate uneti korisničko ime!\n";
            } else if (supervisor.getPassword().isEmpty()) {
                errors += "Morate uneti šifru!\n";
            }

            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }
    
}
