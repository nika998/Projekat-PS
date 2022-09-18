/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.table;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.bg.ac.fon.ps.domain.impl.PrisustvoLetu;

/**
 *
 * @author nikola.dulovic
 */
public class PrisustvaTableModel extends AbstractTableModel {

    private final List<PrisustvoLetu> attendances;
    private final String[] columnNames = new String[]{"Šifra leta", "Vreme ukrcavanja", "Vreme polaska", "Vreme dolaska", "Prevoznik"};
    private final Class[] columnClasses = new Class[]{String.class, Date.class, Date.class, Date.class, String.class};

    public PrisustvaTableModel(List<PrisustvoLetu> attendances) {
        this.attendances = attendances;
    }

    @Override
    public int getRowCount() {
        return attendances == null ? 0 : attendances.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PrisustvoLetu attendance = attendances.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return attendance.getFlight().getIdFlight();
            case 1:
                return attendance.getBoardingTime();
            case 2:
                return attendance.getFlight().getStartTime();
            case 3:
                return attendance.getFlight().getEndTime();
            case 4:
                return attendance.getFlight().getCompany();
            default:
                return null;
        }
    }

    public Object getValueAtRow(int rowIndex) {
        return attendances.get(rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void deleteResults() {
        attendances.clear();
        fireTableDataChanged();
    }

    public void addAttendance(Object response) {
        attendances.add((PrisustvoLetu) response);
        fireTableDataChanged();

    }
}
