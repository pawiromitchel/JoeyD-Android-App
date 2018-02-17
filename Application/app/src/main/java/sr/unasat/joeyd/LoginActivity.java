package sr.unasat.joeyd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.User;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private JoeydDAO joeydDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void doLogin(View view) {
        joeydDAO = new JoeydDAO(this);
        if (!usernameEditText.getText().toString().isEmpty() || !passwordEditText.getText().toString().isEmpty()) {
            User user = joeydDAO.authenticateUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            if(user != null){
                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Het inloggen is mislukt", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
