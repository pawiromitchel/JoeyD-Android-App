package sr.unasat.joeyd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.User;
import sr.unasat.joeyd.fragments.LoginFragment;
import sr.unasat.joeyd.fragments.LogoFragment;

public class LoginScreenActivity extends AppCompatActivity {

    private JoeydDAO joeydDAO;
    private EditText usernameText, passwordText;
    private Button loginBtn;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        usernameText = (EditText) findViewById(R.id.username_frag);
        passwordText = (EditText) findViewById(R.id.password_frag);
        loginBtn = (Button) findViewById(R.id.loginBtn_frag);


        loadFragment(new LogoFragment());
    }

    public void goToLoginFrag(View view) {
        if (view == findViewById(R.id.loginBtn_main)) {
            loadFragment(new LoginFragment());
        }
        if(view == findViewById(R.id.loginBtn_frag)){
            //goToLoginUser(view);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginUser(view);
                }
            });
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.commit();
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(LoginScreenActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void goToLoginUser(){
        Intent intent = new Intent(LoginScreenActivity.this, MainMenuActivity.class);
        Toast.makeText(this, "You are signed in", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void loginUser(View view){
        joeydDAO = new JoeydDAO(this);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if(!username.isEmpty() || !password.isEmpty()) {
            User user = joeydDAO.authenticateUser(username, password);
            if(user != null && !user.getUserName().isEmpty() && !user.getPassword().isEmpty()
                    && user.getUserName().equals(username) && user.getPassword().equals(password)) {
                goToLoginUser();
            }
        } else{
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT);
        }
    }
}
