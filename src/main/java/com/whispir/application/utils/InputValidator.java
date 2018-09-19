package com.whispir.application.utils;

import com.whispir.application.model.Commands;

/**
 * Utility class for input command validation.
 */
public class InputValidator {

    private static final String EXIT_VARIABLE="EXIT";

    /**
     * Method for validating complete input command
     * Validating if place command is in correct format like "PLACE 2,3,NORTH"
     * Validate if it's correct movement command like MOVE, LEFT, RIGHT or for REPORT
     * @param input
     * @return boolean, true for successful validation, false if input is invalid
     */
    public static boolean validateInput(String input) {

        boolean returnValue = false;
        if (isValidPlaceCommand(input) || validateEnumProperty(input) || input.equals(EXIT_VARIABLE)) {
            returnValue = true;
        }
        return returnValue;
    }

    private static boolean validateEnumProperty(String input) {

        for (Commands command : Commands.values()) {
            if (input.equals(command.name())) {
                return true;
            }

        }
        return false;
    }

    /**
     * Matches the PLACE command inputCommand against a regular expression.
     *
     * @param input Place command inputCommand
     * @return TRUE if a valid-formatted command
     */
    public static boolean isValidPlaceCommand(String input) {
        return input.matches("^PLACE\\s{1}[0-9]+,[0-9]+,(NORTH|SOUTH|EAST|WEST)");
    }
}
