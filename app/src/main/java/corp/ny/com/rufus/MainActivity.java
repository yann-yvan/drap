package corp.ny.com.rufus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import corp.ny.com.rufus.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        console = findViewById(R.id.console);
        findViewById(R.id.insert_user).setOnClickListener(this);
        findViewById(R.id.select_user).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert_user:
                User user = User.getInstance();
                user.setId(user.count() + 1);
                user.setName(String.format("User_%s", user.getId()));
                user.setEmail(String.format("User_%s@email.ny", user.getId()));
                user.save();
                writeInConsole(String.format("Insert user :  %s", user));
                break;
            case R.id.select_user:
                for (User user1 : User.getInstance().findAll())
                    writeInConsole(user1.toString());
                break;
            case R.id.clear:
                console.setText("Rufus console");
                break;
        }
    }

    public void writeInConsole(String result) {
        console.append(String.format("\n%s", result));
    }
}
