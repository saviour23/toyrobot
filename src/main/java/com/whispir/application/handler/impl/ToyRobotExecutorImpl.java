package com.whispir.application.handler.impl;

import com.whispir.application.exception.RobotException;
import com.whispir.application.model.CommonConstants;
import com.whispir.application.service.RobotAdaptar;
import com.whispir.application.handler.api.ToyRobotExecutor;
import com.whispir.application.utils.InputValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component("toyRobotExecutor")
public class ToyRobotExecutorImpl implements ToyRobotExecutor {

    private static Boolean keepRunning;
    private static Boolean isFirstMove;
    @Autowired
    private RobotAdaptar adaptar;

    public void executeRobot() throws RobotException {
        BufferedReader commandLineInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your command: ");
        keepRunning = true;
        String inputString = "";
        isFirstMove = true;
        while (keepRunning) {
            try {
                inputString = commandLineInput.readLine();
                System.out.println(parseCommand(inputString));

            } catch (Exception e) {
                throw new RobotException("Exception in Execution", e);
            }
        }


    }

    /**
     * Method responsible to parsing the input command including command validation,
     * validating the first command to be always PLACE and calls appropriate execution methods.
     *
     * @param command command given to robot
     * @return either error string / appropriate command / empty string for success
     */
    private String parseCommand(String command) {
        String outputValue = "";
        command = command.toUpperCase();
        if (!StringUtils.isEmpty(command) && InputValidator.validateInput(command)) {

            if (command.equalsIgnoreCase("EXIT")) {
                keepRunning = false;
                System.out.println("\n\n Exiting RobotMover Simulator, Good Bye!!");
            } else if (isFirstMove) {
                if (adaptar.isPlaceCommand(command)) {
                    adaptar.inputCommand(command);
                    isFirstMove = false;
                } else {
                    outputValue = "First command should be PLACE command. eg. \"place 0,0,north\"";
                }

            } else {

                outputValue = adaptar.inputCommand(command);
            }

        } else {
            outputValue = CommonConstants.ERROR_MSG_INVALID_INPUT;
        }
        return outputValue;
    }

    public void setAdaptar(RobotAdaptar adaptar) {
        this.adaptar = adaptar;
    }

}
