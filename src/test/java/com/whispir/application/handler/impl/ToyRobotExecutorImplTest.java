package com.whispir.application.handler.impl;

import com.whispir.application.service.RobotAdaptar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Field;

public class ToyRobotExecutorImplTest {


    private ToyRobotExecutorImpl executor;
    @Mock
    RobotAdaptar adaptor;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {

        executor = new ToyRobotExecutorImpl();
    }

    /**
     * Testing for positive scenario if first command is PLACE
     */
    @Test
    public void testExecuteForPlace() {
        try {
            executor.setAdaptar(adaptor);
            Mockito.when(adaptor.inputCommand(Mockito.anyString())).thenReturn("");
            Mockito.when(adaptor.isPlaceCommand(Mockito.anyString())).thenReturn(true);

            Class<?> clazz = executor.getClass();

            Field f1 = clazz.getDeclaredField("keepRunning");
            f1.setAccessible(true);
            f1.set(executor, true);
            System.out.println(f1.get(executor));
            Field f2 = clazz.getDeclaredField("isFirstMove");
            f2.setAccessible(true);
            f2.set(executor, true);


            String value = Whitebox.invokeMethod(executor, "parseCommand", "PLACE 2,3,NORTH");
            Assert.assertEquals("", value);
        } catch (Exception e) {
            Assert.fail("Exception occoured in parsing");
        }


    }

    /**
     * Testing for Negative scenario if first command is Not PLACE
     */
    @Test
    public void testFirstMoveCommand() {
        try {
            executor.setAdaptar(adaptor);
            Mockito.when(adaptor.inputCommand(Mockito.anyString())).thenReturn("");
            Mockito.when(adaptor.isPlaceCommand(Mockito.anyString())).thenReturn(false);

            Class<?> clazz = executor.getClass();

            Field f1 = clazz.getDeclaredField("robotApplicationExecutor");
            f1.setAccessible(true);
            f1.set(executor, true);
            System.out.println(f1.get(executor));
            Field f2 = clazz.getDeclaredField("isFirstMove");
            f2.setAccessible(true);
            f2.set(executor, true);


            String value = Whitebox.invokeMethod(executor, "parseCommand", "MOVE");
            Assert.assertEquals("First command should be PLACE command. eg. \"place 0,0,north\"", value);
        } catch (Exception e) {
            Assert.fail("Exception occoured in parsing");
        }


    }

}
