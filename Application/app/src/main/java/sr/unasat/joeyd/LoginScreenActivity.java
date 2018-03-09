package sr.unasat.joeyd;

import android.content.Intent;
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

public class LoginScreenActivity extends AppCompatActivity {

    private JoeydDAO joeydDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(LoginScreenActivity.this,MainMenuActivity.class);
               startActivity(intent);
            }
        });
    }

    /*public void goToLoginFrag(View view){
        loadFragment();
    }

    private void loadFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //transaction.replace(R.id.frameContainer, fragment);
        transaction.commit();

       Intent intent = new Intent();
        LogInFragment loginFragment = new LogInFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, loginFragment).commit();
    }*/

    public void goToSignUp(View view) {
        Intent intent = new Intent(LoginScreenActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
