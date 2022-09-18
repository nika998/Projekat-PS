/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.table;

import java.util.List;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import rs.bg.ac.fon.ps.domain.impl.Putnik;

/**
 *
 * @author nikola.dulovic
 */
public class PutnikTableModel extends AbstractTableModel {

    private final List<Putnik> passengers;
    private final String[] columnNames = new String[]{"Ime", "Prezime", "Jmbg", "Pol"};
    private final Class[] columnClasses = new Class[]{String.class, String.class, String.class, String.class};

    public PutnikTableModel(List<Putnik> passengers) {
        this.passengers = passengers;
    }

    @Override
    public int getRowCount() {
        return passengers == null ? 0 : passengers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Putnik passenger = passengers.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return passenger.getName();
            case 1:
                return passenger.getSurname();
            case 2:
                return passenger.getJmbg();
            case 3:
                return passenger.getGender();
            default:
                return null;
        }
    }

    public Object getValueAtRow(int rowIndex) {
        return passengers.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void editPassengerInfo(Putnik passenger) {
        for (Putnik p : passengers) {
            if (Objects.equals(p.getId(), passenger.getId())) {
                p = passenger;
                fireTableDataChanged();
                break;
            }
        }
    }

    public void deletePassengerInfo(Putnik passenger) {
        for (Putnik p : passengers) {
            if (p.equals(passenger)) {
                passengers.remove(p);
                fireTableDataChanged();
                break;
            }
        }
    }

    public void deleteResults() {
        passengers.clear();
        fireTableDataChanged();
    }

}
