package com.example.mayank.ldqapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

public class ContactFragment extends Fragment {

    private ImageView img_vw_fb, img_vw_insta;

    public static String FACEBOOK_URL = "https://www.facebook.com/ldqmanipal";
    public static String FACEBOOK_PAGE_ID = "ldqmanipal";

    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        img_vw_fb = (ImageView)rootView.findViewById(R.id.imageView);
        img_vw_insta = (ImageView)rootView.findViewById(R.id.imageView2);

        img_vw_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(rootView.getContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        img_vw_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsta();
            }
        });

        return rootView;
    }

    public String getFacebookPageURL(Context context) {

        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    public void openInsta(){

        Uri uri = Uri.parse("http://instagram.com/_u/ldq_manipal");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/ldq_manipal")));
        }
    }

}