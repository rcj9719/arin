package com.example.rcjoshi.arin;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserGuideFragment extends Fragment {
    Button mProceed;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_guide, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        mProceed = (Button) rootView.findViewById(R.id.proceed_btn);
        viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

        mProceed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(0);
            }


        });


        textView.setText("Hello User, this is Arin here! I'm your personal navigation guide to help" +
                " you find your way in this unfamiliar place.\n\n" +
                "Before you proceed, I'd be glad to give you a glimpse of 3 simple steps we'll be carrying out.\n\n" +
                "1. First, I would like to know your destination. That would be the place you are looking for.\n" +
                "2. Next, focus your camera towards the nearest landmark around you so that I'll know just where you are.\n" +
                "3. Voila! All you have to do next is follow the arrows you can see on your screen!\n\n" +
                "Glad to help! Hope you had a nice journey!\n");
        return rootView;
    }


}
