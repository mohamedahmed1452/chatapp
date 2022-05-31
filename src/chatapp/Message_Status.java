package chatapp;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message_Status extends Status{
    private int msgId;
    private String text;
    private int userId;
    private int friedId;
    private int Chat_id;
    public Message_Status() {
        this.setTime(LocalTime.now());
        this.setDate(LocalDate.now());
        this.setSeen(0);
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

  

    public int getFriedId() {
        return friedId;
    }

    public void setFriedId(int friedId) {
        this.friedId = friedId;
    }

    public int getChat_id() {
        return Chat_id;
    }

    public void setChat_id(int Chat_id) {
        this.Chat_id = Chat_id;
    }

 
  

  

  
}
