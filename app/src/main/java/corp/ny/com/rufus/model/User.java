package corp.ny.com.rufus.model;

import android.support.annotation.NonNull;

import corp.ny.com.rufus.database.Model;
import corp.ny.com.rufus.database.Schema;

/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 20/04/18.
 */
public class User extends Model<User> {

    private int id;
    private String name;
    private String email;
    private String password;
    private String deviceToken;
    private String profilePicture;
    private String phone;

    public static User getInstance() {
        return new User();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public void tableStructure(@NonNull Schema table) {
        table.increments("id");
        table.string("name");
        table.string("phone", 15).nullable().defValue("6 XX XX XX XX");
        table.string("email").unique();
        table.string("deviceToken").nullable();
        table.string("profilePicture").nullable();
        table.string("password").nullable();
    }

    @Override
    public String getIdValue() {
        return String.valueOf(id);
    }

}
