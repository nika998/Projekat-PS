/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.fon.ps.logic.impl.stats;

import java.util.List;
import rs.bg.ac.fon.ps.domain.GenericEntity;
import rs.bg.ac.fon.ps.domain.dto.StatisticsDto;
import rs.bg.ac.fon.ps.exception.SOExecutionException;
import rs.bg.ac.fon.ps.logic.SystemOperations;

/**
 *
 * @author nikola.dulovic
 */
public class UcitajStatistiku extends SystemOperations {

    private final StatisticsDto statistics;
    private final int[] statsArray = {0, 0, 0};

    public UcitajStatistiku() {
        statistics = new StatisticsDto(statsArray);
    }

    @Override
    protected void operation() throws SOExecutionException {
        String join = "putnik p ON prisustvo_letu.id_putnik = p.id_putnik RIGHT JOIN let l ON prisustvo_letu.id_let = l.id_let";
        List<GenericEntity> statsList = database.getAll(StatisticsDto.class, join, "", "");
        if (statsList.isEmpty()) {
            genericEntity = statistics;
        } else {
            genericEntity = statsList.get(0);
        }
    }

}
