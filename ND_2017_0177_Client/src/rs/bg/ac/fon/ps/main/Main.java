/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.main;

import java.text.ParseException;
import rs.bg.ac.fon.ps.forms.LoginForm;

/**
 *
 * @author nikola.dulovic
 */
public class Main {
    
    public static void main(String[] args) throws ParseException {
        new LoginForm().setVisible(true);
    }
    
}
