/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.validator.impl;

import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;
import rs.bg.ac.fon.ps.exception.ValidationException;
import rs.bg.ac.fon.ps.validator.Validator;

/**
 *
 * @author nikola.dulovic
 */
public class PrisustvoValidator implements Validator {

    LetValidator flightValidator = new LetValidator();
    PutnikValidator passengerValidator = new PutnikValidator();

    @Override
    public void validate(GenericEntity genericEntity) throws ValidationException {
        try {
            PrisustvoLetu attendance = (PrisustvoLetu) genericEntity;
            String errors = "";

            if (attendance.getBoardingTime().after(attendance.getFlight().getStartTime())) {
                errors += "\n- Vreme ukracavanja mora biti pre vremena polaska";
            }

            try {
                flightValidator.validate(attendance.getFlight());

            } catch (ValidationException e) {
                errors += "\n- " + e.getMessage();
            }

            try {
                passengerValidator.validate(attendance.getPassenger());

            } catch (Exception e) {
                errors += "\n- " + e.getMessage();
            }

            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

}
