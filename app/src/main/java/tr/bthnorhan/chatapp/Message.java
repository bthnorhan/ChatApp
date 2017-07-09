package tr.bthnorhan.chatapp;

/**
 * Created by bthnorhan on 9.07.2017.
 */

public class Message {
    String email,message,time;

    public Message() {}

    public Message(String email, String message, String time) {
        this.email = email;
        this.message = message;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
