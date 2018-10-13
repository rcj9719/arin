package com.example.rcjoshi.arin;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class WayFinderFragment extends Fragment {

    Button mNext,mPrev;
    Spinner mDestination;
    ArrayAdapter<CharSequence> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_way_finder, container, false);

        mDestination = (Spinner) rootView.findViewById(R.id.destinationid);
        mPrev = (Button) rootView.findViewById(R.id.prev_button);
        mNext = (Button) rootView.findViewById(R.id.next_button);
        mAdapter = ArrayAdapter.createFromResource(getContext(),R.array.destinations, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDestination.setAdapter(mAdapter);
        mDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),mDestination.getSelectedItem().toString()+" selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Back to User Guide",Toast.LENGTH_SHORT).show();
            }
        });
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Proceed to Source Capture",Toast.LENGTH_SHORT).show();
            }
        });
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText("WayFinderPage");
        return rootView;
    }

}
