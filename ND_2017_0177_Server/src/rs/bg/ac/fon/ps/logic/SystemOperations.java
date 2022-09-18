/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic;

import java.util.List;
import rs.bg.ac.fon.ps.database.Database;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.exception.ValidationException;
import rs.bg.ac.fon.ps.validator.Validator;

/**
 *
 * @author nikola.dulovic
 */
public abstract class SystemOperations {

    protected Validator validator;
    protected Database database;
    protected GenericEntity genericEntity;
    protected List<GenericEntity> list;
    protected boolean removed;

    protected SystemOperations() {
        database = new Database();
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    protected void checkPreconditions() throws ValidationException {
        if (validator != null) {
            validator.validate(genericEntity);
        }
    }

    protected void connectStorage() throws SOExecutionException {
        getDatabase().connect();
    }

    protected void disconnectStorage() throws SOExecutionException {
        getDatabase().disconnect();
    }

    protected abstract void operation() throws SOExecutionException;

    public void execute() throws SOExecutionException {
        checkPreconditions();
        connectStorage();
        try {
            operation();
            getDatabase().commit();
        } catch (SOExecutionException ex) {
            getDatabase().rollback();
            throw new SOExecutionException(ex.getMessage());
        } finally {
            disconnectStorage();
        }
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void setGenericEntity(GenericEntity genericEntity) {
        this.genericEntity = genericEntity;
    }

    public List<GenericEntity> getList() {
        return list;
    }

    public void setList(List<GenericEntity> list) {
        this.list = list;
    }

}
