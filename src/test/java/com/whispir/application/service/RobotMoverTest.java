package com.whispir.application.service;

import com.whispir.application.model.CommonConstants;
import com.whispir.application.model.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RobotMoverTest {


    private RobotMover robot;

    @Before
    public void before() {
        robot = new RobotMover();
    }

    @Test
    public void shouldNotPlaceRobotInNegativePosition() throws Exception {
        robot.place(-1, -1, Direction.NORTH);
        assertEquals(robot.report(), "No Report preset, Please place the RobotMover.");
    }

    @Test
    public void shouldNotPlaceOutsideTable() throws Exception {
        robot.place(CommonConstants.TABLE_LENGTH + 1, CommonConstants.TABLE_LENGTH + 1, Direction.NORTH);
        assertEquals(robot.report(), "No Report preset, Please place the RobotMover.");
    }

    @Test
    public void shouldIgnoreLeftMoveIfNotPlaced() throws Exception {
        robot.left();
        assertEquals(robot.report(), "No Report preset, Please place the RobotMover.");
    }

    @Test
    public void shouldIgnoreRightMoveIfNotPlaced() throws Exception {
        robot.right();
        assertEquals(robot.report(), "No Report preset, Please place the RobotMover.");
    }

    @Test
    public void shouldIgnoreReportIfNotPlaced() throws Exception {
        robot.report();
        assertEquals(robot.report(), "No Report preset, Please place the RobotMover.");
    }

    @Test
    public void shouldPlaceInTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        assertEquals(robot.report(), "0,0,NORTH");
    }

    @Test
    public void shouldReplaceInTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.place(3, 3, Direction.SOUTH);
        assertEquals(robot.report(), "3,3,SOUTH");
    }

    @Test
    public void shouldRotateLeftOnTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.left();
        assertEquals(robot.report(), "0,0,WEST");
    }

    @Test
    public void shouldRotateLeftMultipleTimesOnTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        for (int i = 0; i < 3; i++) {
            robot.left();
        }
        assertEquals(robot.report(), "0,0,EAST");
    }

    @Test
    public void shouldRotateRightOnTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.right();
        assertEquals(robot.report(), "0,0,EAST");
    }

    @Test
    public void shouldRotateRightMultipleTimesOnTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        for (int i = 0; i < 3; i++) {
            robot.right();
        }
        assertEquals(robot.report(), "0,0,WEST");
    }

    @Test
    public void shouldMoveMultiple() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.move();
        robot.move();
        assertEquals(robot.report(), "0,2,NORTH");
    }

    @Test
    public void movingAndRotatingRobot() throws Exception {
        robot.place(1, 2, Direction.EAST);
        robot.move();
        robot.move();
        robot.left();
        robot.move();
        assertEquals(robot.report(), "3,3,NORTH");
    }


}
