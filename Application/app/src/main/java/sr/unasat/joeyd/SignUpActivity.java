package sr.unasat.joeyd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
    private JoeydDAO joeydDAO;
    private static EditText firstName, lastName, mobileNumber, userName,
            password, confirmPassowrd;
    private static Button signUpBtn_frag;

    private SQLiteDatabase db;
    private Cursor insertCursor;


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

   /* private View initViews(){
        EditText firstName = (EditText) view.findViewById(R.id.firstName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        userName = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassowrd = (EditText) view.findViewById(R.id.confirmPassword);
        signUpBtn_frag = (Button) view.findViewById(R.id.fragment_signUp);
        return view;
    }*/

    //Check validation
    private long signUpUser(User user){
        user = new User();
        String getFirstName = firstName.getText().toString();
        String getLastName = lastName.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getUserName = userName.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassowrd.getText().toString();

        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();
        ContentValues newUser = new ContentValues();
        newUser.put("firstname", getFirstName);
        newUser.put("lastname", getLastName);
        newUser.put("mobilenumber", getMobileNumber);
        newUser.put("username", getUserName);
        newUser.put("password", getPassword);

        long newUserID = db.insert("user", null, newUser);


        // Check if all strings are null or not
        if (getFirstName.equals("") || getFirstName.length() == 0
                || getLastName.equals("") || getLastName.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getUserName.equals("") || getUserName.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0);

        db.close();
        System.out.println(newUserID);
        return newUserID;
    }

    public void signUp(View view) {
        signUpUser(new User());
        signUpBtn_frag.setOnClickListener((View.OnClickListener) this);
        Intent intent = new Intent(SignUpActivity.this, LoginScreenActivity.class);
        Toast.makeText(this, "You have succesfully signed up", Toast.LENGTH_SHORT).show();
    }
}
