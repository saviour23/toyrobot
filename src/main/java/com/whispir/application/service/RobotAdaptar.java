package com.whispir.application.service;

import com.whispir.application.model.Commands;
import com.whispir.application.model.CommonConstants;
import com.whispir.application.model.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotAdaptar {


    @Autowired
    private RobotMover robotMover;

    /**
     * Only checking if command contains "PLACE",
     * validaton for place command has already been done during input validation
     *
     * @param input
     * @return
     */
    public boolean isPlaceCommand(String input) {

        if (input.contains(CommonConstants.PLACE_CONSTANT)) {
            return true;
        }
        return false;
    }

    /**
     * Callss RobotMover method depending on inputCommand.
     *
     * @param input Raw command
     * @return Report of current position of robot or invalid inputCommand error message
     */
    public String inputCommand(String input) {
        String output = "";
        if (isPlaceCommand(input)) {
            callPlaceCommand(input);
        } else {

            Commands command = Commands.valueOf(input);
            switch (command) {
                case MOVE:
                    robotMover.move();
                    break;
                case RIGHT:
                    robotMover.right();
                    break;
                case LEFT:
                    robotMover.left();
                    break;
                case REPORT:
                    output = robotMover.report();
                    break;
                default:
                    return CommonConstants.ERROR_MSG_INVALID_INPUT;

            }

        }

        return output;
    }

    /**
     * Parses the inputCommand and calls the PLACE command.
     *
     * @param input Raw inputCommand
     */
    private void callPlaceCommand(String input) {
        String[] args = input.replaceAll(CommonConstants.PLACE_CONSTANT, "").split(",");

        int x = Integer.parseInt(args[0].trim());
        int y = Integer.parseInt(args[1].trim());
        Direction facing = Direction.valueOf(args[2].toUpperCase());

        robotMover.place(x, y, facing);
    }

    public void setRobotMover(RobotMover mover) {

        this.robotMover = mover;
    }

}
