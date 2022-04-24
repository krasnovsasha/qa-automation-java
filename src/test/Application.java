import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.service.MessageService.processMessage;

class Application {
    public static void main(String[] args) {
        processMessage(MAJOR,"warning1","warning2","warning3","warning4","warning5","warning6");
        processMessage(REGULAR,"regular message1","regular message2","regular message3");
        processMessage(MINOR,"minor message1","minor message2","minor message3","minor message4");
    }
}