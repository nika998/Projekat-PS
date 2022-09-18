/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.validator.impl;

import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Let;
import rs.bg.ac.fon.ps.exception.ValidationException;
import rs.bg.ac.fon.ps.validator.Validator;

/**
 *
 * @author nikola.dulovic
 */
public class LetValidator implements Validator {

    @Override
    public void validate(GenericEntity genericEntity) throws ValidationException {
        try {
            Let flight = (Let) genericEntity;
            String errors = "";

            if (flight.getStartTime().after(flight.getEndTime())) {
                errors += "\n- Vreme polaska mora biti pre vremena dolaska";
            }

            if (flight.getCompany().isEmpty()) {
                errors += "\n- Molimo unesite naziv prevoznika";
            }

            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

}
