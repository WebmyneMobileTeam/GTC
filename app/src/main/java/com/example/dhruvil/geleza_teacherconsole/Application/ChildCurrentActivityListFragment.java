package com.example.dhruvil.geleza_teacherconsole.Application;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.dhruvil.geleza_teacherconsole.R;


public class ChildCurrentActivityListFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ListView listCurrentActivity;


    public static ChildCurrentActivityListFragment newInstance(String param1, String param2) {

        ChildCurrentActivityListFragment FRAGMENT = new ChildCurrentActivityListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        FRAGMENT.setArguments(args);
        return FRAGMENT;

    }

    public ChildCurrentActivityListFragment() {
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

        View convertView = inflater.inflate(R.layout.fragment_child_current_activity_list, container, false);
        listCurrentActivity = (ListView)convertView.findViewById(R.id.listCurrentActivity);

        return convertView;
    }

    @Override
    public void onResume() {
        super.onResume();
        listCurrentActivity.setAdapter(new CurrentActivityAdapter());
    }


    public class CurrentActivityAdapter extends BaseAdapter{

        public CurrentActivityAdapter() {
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = View.inflate(getActivity(),R.layout.item_current_activities,null);
            }

            return convertView;
        }
    }




}
