/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.table;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.bg.ac.fon.ps.domain.impl.AvioLinija;
import rs.bg.ac.fon.ps.domain.impl.KoriscenjeLinija;

/**
 *
 * @author nikola.dulovic
 */
public class KoriscenjeLinijaTableModel extends AbstractTableModel {

    private final List<KoriscenjeLinija> usedLines;
    private final String[] columnNames = new String[]{"Polazano mesto", "Destinacija"};
    private final Class[] columnClasses = new Class[]{String.class, String.class};

    public KoriscenjeLinijaTableModel(List<KoriscenjeLinija> usedLines) {
        this.usedLines = usedLines;
    }

    @Override
    public int getRowCount() {
        return usedLines == null ? 0 : usedLines.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KoriscenjeLinija line = usedLines.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return line.getLine().getStart();
            case 1:
                return line.getLine().getDestination();
            default:
                return null;
        }
    }

    public Object getValueAtRow(int rowIndex) {
        return usedLines.get(rowIndex);
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

    public void deleteResults() {
        usedLines.clear();
        fireTableDataChanged();
    }

    public List<AvioLinija> getLinesList() {
        List<AvioLinija> list = new LinkedList<>();
        for (KoriscenjeLinija kl : usedLines) {
            list.add(kl.getLine());
        }
        return list;

    }

}
