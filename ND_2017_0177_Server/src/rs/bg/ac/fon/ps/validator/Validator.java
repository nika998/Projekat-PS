/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.validator;

import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.exception.ValidationException;

/**
 *
 * @author nikola.dulovic
 */
public interface Validator {

    public void validate(GenericEntity genericEntity) throws ValidationException;

}
