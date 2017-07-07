package com.example.mayank.ldqapp;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import static android.widget.Toast.LENGTH_SHORT;

public class QuizFragment extends Fragment {

    EditText editText_file;
    Button button_down;

    public QuizFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

        editText_file = (EditText)rootView.findViewById(R.id.editText);
        button_down = (Button)rootView.findViewById(R.id.buttonChoose);

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        final StorageReference storageReference = firebaseStorage.getReferenceFromUrl("gs://ldq-app-d2e6b.appspot.com");

        button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fileName = new String(editText_file.getText().toString());

                StorageReference childReference = storageReference.child("Quizzes");

                StorageReference fileReference = storageReference.child("Quizzes/" + fileName + ".pdf");

                File rootPath = new File(Environment.getExternalStorageDirectory(),"LDQ");

                if(!rootPath.exists()){
                    rootPath.mkdirs();
                }

                final File localFile = new File(rootPath,fileName);

                fileReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(),"File downloaded",LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Download failed. Try again!", LENGTH_SHORT).show();
                    }
                });
                //Toast.makeText(getContext(),"Button working",LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

}