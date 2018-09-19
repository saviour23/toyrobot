package com.whispir.application.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {
    @Test
    public void shouldReturnNextDirection() {
        assertEquals(Direction.EAST, Direction.NORTH.getNext());
        assertEquals(Direction.NORTH, Direction.WEST.getNext());
        assertEquals(Direction.SOUTH, Direction.EAST.getNext());
        assertEquals(Direction.WEST, Direction.SOUTH.getNext());
    }

    @Test
    public void shouldReturnPreviousDirection() {
        assertEquals(Direction.EAST, Direction.SOUTH.getPrev());
        assertEquals(Direction.WEST, Direction.NORTH.getPrev());
        assertEquals(Direction.NORTH, Direction.EAST.getPrev());
        assertEquals(Direction.SOUTH, Direction.WEST.getPrev());
    }

}