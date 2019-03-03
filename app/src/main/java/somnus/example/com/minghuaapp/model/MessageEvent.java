package somnus.example.com.minghuaapp.model;

/**
 * Created by Somnus on 2019/2/27.
 * EventBus
 */

public class MessageEvent{
    private String message;
    public  MessageEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}