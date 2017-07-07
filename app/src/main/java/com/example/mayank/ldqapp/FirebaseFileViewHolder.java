package com.example.mayank.ldqapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Models.Files;

/**
 * Created by Mayank on 27-01-2017.
 */

public class FirebaseFileViewHolder extends RecyclerView.ViewHolder {

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 50;

    public TextView fileName;
    public Button download;
    public CardView cardView;

    View mView;
    Context mContext;

    public FirebaseFileViewHolder(View itemView) {

        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

        cardView = (CardView) mView.findViewById(R.id.card_view);
        fileName = (TextView) mView.findViewById(R.id.fileName);
        download = (Button) mView.findViewById(R.id.downloadButton);
    }

    public void bindFile(Files files, View.OnClickListener dwnld) {

        fileName.setText(files.getFileName());
        download.setOnClickListener(dwnld);
    }

    public String getName(){
        return fileName.getText().toString();
    }

    public Button getButton(){
        return download;
    }
}

