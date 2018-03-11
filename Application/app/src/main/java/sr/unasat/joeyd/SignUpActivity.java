package sr.unasat.joeyd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.User;

public class SignUpActivity extends AppCompatActivity {

    private static View view;
    private static EditText firstName, lastName, mobileNumber, userName,
            password, confirmPassowrd;
    private static Button signUpBtn_frag;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPassowrd = (EditText) findViewById(R.id.confirmPassword);
        signUpBtn_frag = (Button) findViewById(R.id.fragment_signUp);
    }

    public void signUp(View view) {
        if(!firstName.getText().toString().isEmpty() || !lastName.getText().toString().isEmpty() || !mobileNumber.getText().toString().isEmpty() || !userName.getText().toString().isEmpty() || !password.getText().toString().isEmpty()){
            if(password.getText().toString().equals(confirmPassowrd.getText().toString())){
                SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
                db = joeyDDatabaseHelper.getReadableDatabase();

                ContentValues newUserValues = new ContentValues();
                newUserValues.put("first_name", firstName.getText().toString());
                newUserValues.put("last_name", lastName.getText().toString());
                newUserValues.put("mobile_number", mobileNumber.getText().toString());
                newUserValues.put("username", userName.getText().toString());
                newUserValues.put("password", password.getText().toString());
                long newUser = db.insert("user", null, newUserValues);
                db.close();
            } else {
                Toast.makeText(this, "Your password does not match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(SignUpActivity.this, LoginScreenActivity.class);
        Toast.makeText(this, "You have succesfully signed up", Toast.LENGTH_SHORT).show();
    }
}
