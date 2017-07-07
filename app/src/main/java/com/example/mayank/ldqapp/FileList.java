package com.example.mayank.ldqapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Mayank on 28-01-2017.
 */

public class FileList extends QuizFragment {

    public FileList(){

    }

    public Query getQuery(DatabaseReference databaseReference) {
        Query allWordsQuery = databaseReference.child("files")
                .limitToFirst(100);


        return allWordsQuery;
    }
}
