package com.whispir.application;

import com.whispir.application.exception.RobotException;
import com.whispir.application.handler.api.ToyRobotExecutor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Robot Simulator Application
 * <p>
 * Main class to start the Simulator
 */
public class ToyRobotApplication {

    private static ToyRobotExecutor robotApplicationExecutor;

    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            context.scan("com.whispir.application");
            context.refresh();
            robotApplicationExecutor = (ToyRobotExecutor) context.getBean("toyRobotExecutor");
            //Commands that robot can follow
            System.out.println("Welcome to Robot Simulator, \n\t\tRobot can move in a square of 5x5 ");
            System.out.println("\nSupported commands are" +
                    "\n 1) PLACE <x-axis>,<y-axis>,<DIRECTION> \n\t\tfor eg. PLACE 2,3,NORTH \n\t\tx & y can be upto 5 and direction can be NORTH, EAST, SOUTH, WEST" +
                    "\n 2) MOVE \n\t\t robot will move toward direction provided in PLACE command" +
                    "\n 3) LEFT \n\t\t Robot will turn to left direction on same place" +
                    "\n 4) RIGHT \n\t\t Robot will turn to right direction on same place" +
                    "\n 5) REPORT \n\t\t to check the robot location" +
                    "\n 6) EXIT \n\t\t To exit Robot Simulator");

            robotApplicationExecutor.executeRobot();
        } catch (Exception e) {
            System.out.println("Exception Occoured in Robot movement " + e.getMessage());
        }

    }


}
