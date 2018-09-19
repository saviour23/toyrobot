package com.whispir.application.service;

import com.whispir.application.model.Direction;
import com.whispir.application.model.CommonConstants;
import org.springframework.stereotype.Service;

/**
 * RobotMover class responsible to store the location of robot and
 * move the robot with handling following commands:- MOVE, LEFT, RIGHT
 */
@Service
public class RobotMover {

    private boolean placed;
    private int x;
    private int y;
    private Direction facing;

    /**
     * Method to place the robot in specified position
     * @param x X-Axis, horizontal position
     * @param y Y-Axis vertical position
     * @param facing director like NORTH or EAST
     * @return
     */
    public boolean place(int x, int y, Direction facing) {
        if (isValidPlacement(x) && isValidPlacement(y)) {
            this.x = x;
            this.y = y;
            this.facing = facing;
            placed = true;
            return true;
        }
        return false;
    }

    /**
     * Method will return the current location of Robot
     * @return
     */
    public String report() {
        if (placed) {
            return (x + "," + y + "," + facing).toUpperCase();
        }
        return "No Report preset, Please place the RobotMover.";
    }

    /**
     * Change the direction of robot on the same location.
     * for eg. if robot was facing NORTH then now it will face WEST.
     * @return true for success and false if not success
     */
    public boolean left() {
        if (placed) {
            facing = facing.getPrev();
            return true;
        }
        return false;
    }
    /**
     * Change the direction of robot on the same location.
     * for eg. if robot was facing NORTH then now it will face EAST.
     * @return true for success and false if not success
     */
    public boolean right() {
        if (placed) {
            facing = facing.getNext();
            return true;
        }
        return false;
    }

    /**
     * Methos responsible to move one step in the facing direction of robot
     * @return
     */
    public boolean move() {
        if (placed) {
            moveForward();
            return true;
        }
        return false;
    }

    /**
     * Depending on the robot's direction, move it forward/back across the table X or Y plane.
     * East and North are positive while south and west are negative.
     *
     * If north then increment y axis by 1
     * if east then increment x axis by 1
     * if south the decrement y axis by 1
     * if west then decrement x axis by 1
     *
     */
    private void moveForward() {
        switch (facing) {
            case NORTH:
                if (isValidPlacement(y + 1)) {
                    y++;
                }
                break;
            case SOUTH:
                if (isValidPlacement(y - 1)) {
                    y--;
                }
                break;
            case EAST:
                if (isValidPlacement(x + 1)) {
                    x++;
                }
                break;
            case WEST:
                if (isValidPlacement(x - 1)) {
                    x--;
                }
                break;
        }
    }

    /**
     * Checks if position is on the table.
     *
     * @param pos Proposed position
     * @return TRUE if position is on the table, false if it's not fitting in table
     */
    private static boolean isValidPlacement(int pos) {
        if (pos >= 0 && pos < CommonConstants.TABLE_LENGTH) {
            return true;
        }

        return false;
    }
}
