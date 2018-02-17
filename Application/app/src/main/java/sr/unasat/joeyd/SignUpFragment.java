package sr.unasat.joeyd;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import sr.unasat.joeyd.entity.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static EditText firstName, lastName, mobileNumber, userName,
        password, confirmPassowrd;
    private static Button signUpBtn;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        signUpBtn.setOnClickListener(this);
        return view;
    }

    //initialize all View components
    private void initViews(){
        firstName = (EditText) view.findViewById(R.id.firstName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        password = (EditText) view.findViewById(R.id.password);
        userName = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassowrd = (EditText) view.findViewById(R.id.confirmPassword);
        signUpBtn = (Button) view.findViewById(R.id.signUpBtn);
    }

    @Override
    public void onClick(View view) {
        signUpBtn.setOnClickListener(this);
    }

    //Check validation
    private void checkValidation(User user){
        user = new User();
        String getFirstName = firstName.getText().toString();
        String getLastName = lastName.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getUserName = userName.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassowrd.getText().toString();

        // Check if all strings are null or not
        if (getFirstName.equals("") || getFirstName.length() == 0
                || getLastName.equals("") || getLastName.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getUserName.equals("") || getUserName.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0);
        }
}
