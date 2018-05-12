package corp.ny.com.rufus.model;

import android.support.annotation.NonNull;

import corp.ny.com.rufus.database.Constraint;
import corp.ny.com.rufus.database.Model;
import corp.ny.com.rufus.database.Schema;


/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 19/04/18.
 */
public class Message extends Model<Message> {
    private int id;
    private String message;
    private int receiverId;
    private int senderId;

    public static Message getInstance() {
        return new Message();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Override
    public String getIdValue() {
        return String.valueOf(id);
    }

    @Override
    public void tableStructure(@NonNull Schema table) {
        table.integer("id");
        table.primary("id");
        table.text("message");
        table.integer("senderId");
        table.integer("receiverId");
        table.foreign("senderId")
                .references(User.getInstance().getIdName())
                .on(User.getInstance().getTableName())
                .onDelete(Constraint.Action.CASCADE)
                .onUpdate(Constraint.Action.CASCADE);
        table.foreign("receiverId")
                .references(User.getInstance().getIdName())
                .on(User.getInstance().getTableName())
                .onDelete(Constraint.Action.CASCADE)
                .onUpdate(Constraint.Action.CASCADE);
    }
}
