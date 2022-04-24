import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.service.MessageService.processMessage;

class Application {
    public static void main(String[] args) {
        //test if 1st param IS NULL
        processMessage(MAJOR,null,"warning2","warning3","warning4","warning5","warning6");
        //test if 2nd param IS NULL
        processMessage(REGULAR,"regular message1",null,"regular message3");
        //test if all incoming params are NULL
        processMessage(MINOR,null,null,null);

    }
}