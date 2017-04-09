package com.example.mayank.ldqapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class RecruitmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private DatabaseReference mDatabase;
    //private FirebaseAuth mAuth;

    EditText name_text,reg_text, mob_txt,interest_txt;
    Button button;
    Spinner spinner;
    String item;

    public RecruitmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recruitment, container, false);

        name_text = (EditText)rootView.findViewById(R.id.editText2);
        reg_text = (EditText)rootView.findViewById(R.id.editText3);
        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        mob_txt = (EditText)rootView.findViewById(R.id.editText5);
        interest_txt = (EditText)rootView.findViewById(R.id.editText6);

        button = (Button)rootView.findViewById(R.id.button2);

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("I");
        categories.add("II");
        categories.add("III");
        categories.add("IV");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("recruitment");

        //mAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name,regNo,mobNo,interest;
                int year;

                name = name_text.getText().toString();
                regNo = reg_text.getText().toString();
                mobNo = mob_txt.getText().toString();
                interest = interest_txt.getText().toString();

                RecruitUser recruitUser = new RecruitUser(name,regNo,item,mobNo,interest);

                if((TextUtils.isEmpty(name)) || (TextUtils.isEmpty(regNo)) || (TextUtils.isEmpty(item))
                        || (TextUtils.isEmpty(interest)) || (TextUtils.isEmpty(mobNo))) {

                    Toast.makeText(getContext(),"Fill all fields!",Toast.LENGTH_SHORT).show();

                }
                else {

                    mDatabase.child(LDQApp.uid).setValue(recruitUser);

                    mDatabase.child(LDQApp.uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            RecruitUser sUser = dataSnapshot.getValue(RecruitUser.class);
                            Toast.makeText(getContext(), "Submitted successfully", Toast.LENGTH_SHORT).show();

                            Log.d(TAG, "User name: " + name + ", Registration No: " + regNo);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });
                }
            }
        });

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        item = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        item = null;
    }
}