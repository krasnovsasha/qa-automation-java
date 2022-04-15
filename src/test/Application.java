import com.tcs.edu.service.MessageService;
import com.tcs.edu.enums.Severity;

class Application {
    public static void main(String[] args) {
        new MessageService().processMessage(Severity.REGULAR, "Message");
        new MessageService().processMessage(Severity.REGULAR, "Another message");
        new MessageService().processMessage(Severity.MINOR, "Minor message");
        new MessageService().processMessage(Severity.REGULAR, "Another message");
        new MessageService().processMessage(Severity.MAJOR, "Warning");
        new MessageService().processMessage(Severity.MAJOR, "Another warning");
        new MessageService().processMessage(Severity.MINOR, "Minor message");
    }
}