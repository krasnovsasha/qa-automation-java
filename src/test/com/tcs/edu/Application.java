package com.tcs.edu;

import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.OutputOrder.*;
import static com.tcs.edu.enums.Doubling.*;
import static com.tcs.edu.service.MessageService.log;

class Application {
    public static void main(String[] args) {
        //test if 1st param IS NULL
        log(MAJOR, (String) null, "warning2", "warning3", "warning4", "warning5", "warning6");
        //test if 2nd param IS NULL
        log(REGULAR, "regular message1", null, "regular message3");
        //test if all incoming params are NULL
        log(MINOR, (String) null, (String) null);
        //test for DESC
        log(MAJOR,DESC, "message1", "message2", "message3");
        //test for ASC
        log(MAJOR,ASC, "message1", "message2", "message3");
        //test for DISTINCT messages
        log(MAJOR, DESC, DISTINCT,"messageTheSame","messageTheSame","messageNotTheSame");
        //test for DOUBLING messages
        log(MAJOR, ASC, DOUBLES,"messageTheSame","messageTheSame","messageNotTheSame");
    }
}