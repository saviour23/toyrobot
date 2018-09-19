package com.whispir.application.handler.api;

import com.whispir.application.exception.RobotException;

/**
 * Interface for Toy Executor.
 * Responsible for reading commands and moving robot based on those commands
 */
public interface ToyRobotExecutor {
    /**
     * Public method exposed to start the execution of Simulator.
     * It will internall read the input command from command line and proceed accordingly
     * @throws RobotException if exception occours while moving robot
     */
    public void executeRobot() throws RobotException;
}
