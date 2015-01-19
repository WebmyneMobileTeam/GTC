package com.example.dhruvil.geleza_teacherconsole.UploadResults;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dhruvil.geleza_teacherconsole.Application.MainActivity;
import com.example.dhruvil.geleza_teacherconsole.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUploadResults#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUploadResults extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ImageView img;

    public static FragmentUploadResults newInstance(String param1, String param2) {
        FragmentUploadResults fragment = new FragmentUploadResults();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentUploadResults() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View convertView = inflater.inflate(R.layout.fragment_fragment_upload_results, container, false);

        img = (ImageView)convertView.findViewById(R.id.imgMainUploarResults);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView iSide = (ImageView)convertView.findViewById(R.id.imgRightUploadResult);
                if(iSide.getVisibility() == View.INVISIBLE){
                    iSide.setVisibility(View.VISIBLE);
                    img.setImageResource(R.drawable.upload_result_two);
                }
            }
        });


        return convertView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setUploadResultsStrips();
    }
}
