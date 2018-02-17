package sr.unasat.joeyd;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment{

    private static View view;
    private static EditText firstName, lastName, mobileNumber,
        password, confirmPassowrd;
    private static Button signUpBtn;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initViews();
        return inflater.inflate(R.layout.signup_layout, container, false);
    }

    //initialize all View components
    private void initViews(){
        firstName = (EditText) view.findViewById(R.id.firstName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassowrd = (EditText) view.findViewById(R.id.confirmPassword);
    }
}
