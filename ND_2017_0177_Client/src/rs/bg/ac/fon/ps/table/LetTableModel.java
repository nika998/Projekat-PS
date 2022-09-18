/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.table;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import rs.bg.ac.fon.ps.domain.impl.Let;

/**
 *
 * @author nikola.dulovic
 */
public class LetTableModel extends AbstractTableModel {

    private List<Let> flights;
    private final String[] columnNames = new String[]{"Destinacija", "Šifra leta", "Vreme polaska", "Vreme dolaska", "Prevoznik"};
    private final Class[] columnClasses = new Class[]{String.class, String.class, String.class, String.class, String.class};

    public LetTableModel(List<Let> flights) {
        this.flights = flights;
    }

    @Override
    public int getRowCount() {
        return flights == null ? 0 : flights.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Let flight = flights.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flight.getLine().getDestination();
            case 1:
                return flight.getIdFlight();
            case 2:
                return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(flight.getStartTime());
            case 3:
                return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(flight.getEndTime());
            case 4:
                return flight.getCompany();
            default:
                return null;
        }
    }

    public Object getValueAtRow(int rowIndex) {
        return flights.get(rowIndex);
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

    public void editLetInfo(Let flight) {
        for (Let l : flights) {
            if (Objects.equals(l.getIdFlight(), flight.getIdFlight())) {
                l = flight;
                fireTableDataChanged();
                break;
            }
        }
    }

    public void deleteLetInfo(Let flight) {
        for (Let l : flights) {
            if (l.equals(flight)) {
                flights.remove(l);
                fireTableDataChanged();
                break;
            }
        }
    }

    public void setList(List<Let> flights) {
        this.flights = flights;
        fireTableDataChanged();
    }

    public void deleteResults() {
        flights.clear();
        fireTableDataChanged();
    }
}
