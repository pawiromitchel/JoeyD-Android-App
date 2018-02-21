package sr.unasat.joeyd;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Button signUp = (Button) findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment();
            }
        });
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

    public void goToSignUp(View view){
        loadFragment();
    }

    private void loadFragment(){
       /* FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.commit();*/

       Intent intent = new Intent();
        SignUpFragment signUpFragment = new SignUpFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, signUpFragment).commit();
    }
}
