package com.binhdc.microservice.notificationservice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
    private String employeeId;
    private String message;

    @Override
    public String toString() {
        return "Message [employeeId=" + employeeId + ", message=" + message + "]";
    }

}
