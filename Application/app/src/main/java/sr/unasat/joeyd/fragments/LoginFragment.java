package sr.unasat.joeyd.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.joeyd.LoginScreenActivity;
import sr.unasat.joeyd.MainMenuActivity;
import sr.unasat.joeyd.R;
import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

        public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


}