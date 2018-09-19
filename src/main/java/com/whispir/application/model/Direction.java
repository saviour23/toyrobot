package com.whispir.application.model;

/**
 * Enum class defining direction and provides next or previous direction.
 */
public enum Direction {

    //Defined in clockwise direction.
    NORTH, EAST, SOUTH, WEST;

    /**
     * Returns the next direction in the sequence.
     * When user input is to turn right then it means the change to next direction in clockwise.
     * eg. if initially position was NORTH then after turning right it will be EAST
     *
     * @return  Next enum
     */
    public Direction getNext() {
        if (this.ordinal() < Direction.values().length - 1) {
            return Direction.values()[this.ordinal() + 1];
        }
        else {
            return Direction.values()[0];
        }
    }

    /**
     * Returns the previous direction in the sequence.
     * When user input is to turn left then it means the change to previous direction in anti-clockwise.
     * eg. if initially position was NORTH then after turning left it will be WEST
     *
     * @return  Previous enum
     */
    public Direction getPrev() {
        if (this.ordinal() == 0) {
            return Direction.values()[Direction.values().length - 1];
        }
        else {
            return Direction.values()[this.ordinal() - 1];
        }
    }
}
