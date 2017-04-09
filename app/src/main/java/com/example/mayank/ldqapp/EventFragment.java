package com.example.mayank.ldqapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class EventFragment extends Fragment {

    ListView listView;
    private String[] event_list;
    ArrayAdapter<String> eAdapter;

    public EventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        listView = (ListView)rootView.findViewById(R.id.list_event);

        event_list = getResources().getStringArray(R.array.events_list);

        eAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,event_list);

        listView.setAdapter(eAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String msg = new String();

                switch (position) {
                    case 0:
                        msg = getResources().getString(R.string.quizzes);
                        break;
                    case 1:
                        msg = getResources().getString(R.string.slam);
                        break;
                    case 2:
                        msg = getResources().getString(R.string.mitdt);
                        break;
                    case 3:
                        msg = getResources().getString(R.string.litstock);
                        break;
                    case 4:
                        msg = getResources().getString(R.string.bazinga);
                        break;
                    case 5:
                        msg = getResources().getString(R.string.debwork);
                        break;
                    case 6:
                        msg = getResources().getString(R.string.qi);
                        break;
                    case 7:
                        msg = getResources().getString(R.string.ps);
                        break;
                    case 8:
                        msg = getResources().getString(R.string.ergo);
                        break;
                    case 9:
                        msg = getResources().getString(R.string.potpourri);
                        break;
                    case 10:
                        msg = getResources().getString(R.string.qmq);
                        break;
                    case 11:
                        msg = getResources().getString(R.string.debsoc);
                        break;
                    default:
                        msg = "Invalid";
                }

                AlertDialog.Builder ad_build = new AlertDialog.Builder(getContext());

                ad_build.setMessage(msg).setCancelable(true).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = ad_build.create();
                alert.setTitle((String) listView.getItemAtPosition(position));
                alert.show();

                Button alertButton = alert.getButton(DialogInterface.BUTTON_NEUTRAL);

                if(alertButton!=null) {
                    alertButton.setTextColor(getResources().getColor(R.color.white_btn));
                }


            }
        });

        return rootView;
    }

}