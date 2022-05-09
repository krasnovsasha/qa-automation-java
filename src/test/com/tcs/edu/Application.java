package com.tcs.edu;

import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;
import static com.tcs.edu.service.MessageService.processMessage;

class Application {
    public static void main(String[] args) {
        //test if 1st param IS NULL
        processMessage(MAJOR, (String) null, "warning2", "warning3", "warning4", "warning5", "warning6");
        //test if 2nd param IS NULL
        processMessage(REGULAR, "regular message1", null, "regular message3");
        //test if all incoming params are NULL
        processMessage(MINOR, (String) null, (String) null);
        //test for DESC
        processMessage(MAJOR,DESC, "message1", "message2", "message3");
        //test for ASC
        processMessage(MAJOR,ASC, "message1", "message2", "message3");
        //test for DISTINCT messages
        processMessage(MAJOR, DESC, DISTINCT,"messageTheSame","messageTheSame","messageNotTheSame");
        //test for DOUBLING messages
        processMessage(MAJOR, ASC, DOUBLES,"messageTheSame","messageTheSame","messageNotTheSame");
    }
}