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
public class LogInFragment extends Fragment implements View.OnClickListener{

    private static View view;
    private static EditText username, password;
    private static Button loginBtn_frag;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        loginBtn_frag.setOnClickListener(this);
        return view;
    }

    private void initViews(){
        username = (EditText) view.findViewById(R.id.usernameEditText);
        password = (EditText) view.findViewById(R.id.passwordEditText);
        loginBtn_frag = (Button) view.findViewById(R.id.fragment_logIn);
    }

    public void onClick(View view){
        loginBtn_frag.setOnClickListener(this);
    }

    private void checkValidation(User user){
        user = new User();
    }
}
