package com.rolandoamarillo.workportalinboxbizagi;

import com.rolandoamarillo.workportalinboxbizagi.models.Task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class TaskTest {

    @Mock
    Task mockTask;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNumberOfDaysSameDayTest() {
        when(mockTask.getBeginDate()).thenReturn(new Date());
        when(mockTask.getEndDate()).thenReturn(new Date());

        assertThat("Calculo de los días erróneo", mockTask.calculateDays(mockTask.getBeginDate(), mockTask.getEndDate()), is(0L));
    }

    @Test
    public void getNumberOfDaysDifferentDaysTest() {
        when(mockTask.getBeginDate()).thenReturn(Calendar.getInstance().getTime());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 3);
        when(mockTask.getEndDate()).thenReturn(c.getTime());

        assertThat("Calculo de los días erróneo", mockTask.calculateDays(mockTask.getBeginDate(), mockTask.getEndDate()), is(3L));
    }
}
