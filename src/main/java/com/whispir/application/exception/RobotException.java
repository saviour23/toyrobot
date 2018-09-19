package com.whispir.application.exception;


public class RobotException extends Exception {

    public RobotException(String msg) {
        super(msg);
    }

    public RobotException(String msg, Throwable t) {
        super(msg, t);
    }
}
