package com.whispir.application.service;

import com.whispir.application.exception.RobotException;
import com.whispir.application.model.Direction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RobotAdaptorTest {
    private static String STUB_REPORT = "mock report";

    private RobotAdaptar adapter;
    @Mock
    private RobotMover robotMover;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        adapter = new RobotAdaptar();
        adapter.setRobotMover(robotMover);
    }

    @Test
    public void shouldThrowExceptionIfRobotMoverIsNull(){
        Mockito.when(robotMover.move()).thenThrow(RobotException.class);
        thrown.expect(RobotException.class);
        adapter.inputCommand("MOVE");
        adapter.inputCommand("LEFT");
    }

    @Test
    public void shouldPlaceMethodCalled() throws Exception {
        adapter.inputCommand("PLACE 123,456,NORTH");
        verify(robotMover).place(123, 456, Direction.NORTH);
    }

    @Test
    public void shouldMoveMethodCalled() throws Exception {
        adapter.inputCommand("MOVE");
        verify(robotMover).move();
    }

    @Test
    public void shouldLeftMethodCalled() throws Exception {
        adapter.inputCommand("LEFT");
        verify(robotMover).left();
    }

    @Test
    public void shouldRightMethodCalled() throws Exception {
        adapter.inputCommand("RIGHT");
        verify(robotMover).right();
    }

    @Test
    public void shouldCallReport() throws Exception {
        when(robotMover.report()).thenReturn(STUB_REPORT);
        assertEquals(STUB_REPORT, adapter.inputCommand("REPORT"));
    }

}
