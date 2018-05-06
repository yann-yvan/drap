package corp.ny.com.rufus.model;

import android.support.annotation.NonNull;

import corp.ny.com.rufus.database.Model;
import corp.ny.com.rufus.database.Schema;


/**
 * Created by yann-yvan on 15/11/17.
 */

public class Picture extends Model<Picture> {
    private int id;
    private String state;
    private String path;

    public static Picture getInstance() {
        return new Picture();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getIdValue() {
        return null;
    }

    @Override
    public Picture thisInstance() {
        return this;
    }

    @Override
    public void tableStructure(@NonNull Schema table) {
    }
}