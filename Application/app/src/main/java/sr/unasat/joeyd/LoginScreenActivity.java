package sr.unasat.joeyd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
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
import sr.unasat.joeyd.fragments.LoginFragment;
import sr.unasat.joeyd.fragments.LogoFragment;
import sr.unasat.joeyd.services.SyncSpecialsWithDatabaseService;

public class LoginScreenActivity extends AppCompatActivity {

    private JoeydDAO joeydDAO;
    private EditText username, password;
    private Button loginBtn;
    private SQLiteDatabase db;
    private Cursor getUserCreds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = (EditText) findViewById(R.id.username_frag);
        password = (EditText) findViewById(R.id.password_frag);
        loadFragment(new LogoFragment());

        // start the SyncSpecialsWithDatabaseService
        Intent startSyncSpecialsWithDatabaseService = new Intent(this, SyncSpecialsWithDatabaseService.class);
        startService(startSyncSpecialsWithDatabaseService);
    }

    public void goToLoginFrag(View view) {
        if (view == findViewById(R.id.loginBtn_main)) {
            loadFragment(new LoginFragment());
        }
        if (view == findViewById(R.id.loginBtn_frag)) {
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

    public void goToLoginUser() {
        Intent intent = new Intent(LoginScreenActivity.this, MainMenuActivity.class);
        Toast.makeText(this, "You are signed in", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void loginUser(View view) {

        view = getSupportFragmentManager().findFragmentById(R.id.frameContainer).getView();
        username = view.findViewById(R.id.username_frag);
        password = view.findViewById(R.id.password_frag);

        String userInput = username.getText().toString();
        String passwordInput = password.getText().toString();

        joeydDAO = new JoeydDAO(this);
        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();

        getUserCreds = db.rawQuery(String.format("select * from user where 'username' = '%s' AND 'password' = '%s'", userInput, passwordInput),
                null);

        if (!userInput.isEmpty() || !passwordInput.isEmpty()) {
            User user = joeydDAO.authenticateUser(userInput, passwordInput);
            if (user != null && !user.getUserName().isEmpty() && !user.getPassword().isEmpty()
                    && user.getUserName().equals(userInput) && user.getPassword().equals(passwordInput)) {

                // store the UserID into the SharedPreferneces
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("LoggedUserID", user.getId());
                editor.apply();

                goToLoginUser();
            } else {
                Toast.makeText(this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
