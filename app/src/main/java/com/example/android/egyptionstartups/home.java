package com.example.android.egyptionstartups;



import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
ImageView image1,image2,image3,image4;
TextView txt1,txt2,txt3,txt4;


    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        image1=v.findViewById(R.id.imageView);
        image2=v.findViewById(R.id.imageView2);
        image3=v.findViewById(R.id.imageView3);

        txt1=v.findViewById(R.id.textView);
        txt2=v.findViewById(R.id.textView2);
        txt3=v.findViewById(R.id.textView3);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToWhatIsStartUP();
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToWhatIsStartUP();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToHowToRegister();
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToHowToRegister();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToStartUpStories();
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToStartUpStories();
            }
        });



        return v;
    }
    public void moveToWhatIsStartUP(){
        getFragmentManager().beginTransaction().replace(R.id.content,new what_is_startup()).addToBackStack(null).commit();
    }

    public void moveToHowToRegister(){
        getFragmentManager().beginTransaction().replace(R.id.content,new how_to_register()).addToBackStack(null).commit();
    }

    public void moveToStartUpStories(){
        Intent i = new Intent(getActivity(), StartUpStoriesActivity.class);
        startActivity(i);
        //getFragmentManager().beginTransaction().replace(R.id.content,new startup_stories()).addToBackStack(null).commit();
    }


}
