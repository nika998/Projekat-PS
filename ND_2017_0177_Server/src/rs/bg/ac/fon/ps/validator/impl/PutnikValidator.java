/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.validator.impl;

import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.impl.Putnik;
import rs.bg.ac.fon.ps.exception.ValidationException;
import rs.bg.ac.fon.ps.validator.Validator;

/**
 *
 * @author nikola.dulovic
 */
public class PutnikValidator implements Validator {

    @Override
    public void validate(GenericEntity genericEntity) throws ValidationException {
        try {
            Putnik putnik = (Putnik) genericEntity;
            String errors = "";

            if (putnik.getName().isEmpty()) {
                errors += "\n- Molimo unesite ime putnika";
            }

            if (putnik.getSurname().isEmpty()) {
                errors += "\n- Molimo unesite prezime putnika";
            }

            if (putnik.getJmbg().isEmpty()) {
                errors += "\n- Molimo unesite matični broj putnika";
            }

            if (putnik.getGender().isEmpty()) {
                errors += "\n- Molimo unesite pol putnika";
            }

            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

}
