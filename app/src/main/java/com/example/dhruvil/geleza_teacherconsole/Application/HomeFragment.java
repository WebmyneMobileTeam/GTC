package com.example.dhruvil.geleza_teacherconsole.Application;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dhruvil.geleza_teacherconsole.CustomViews.PagerSlidingTabStrip;
import com.example.dhruvil.geleza_teacherconsole.R;


public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private String[] TITLES={ "News", "All Posts", "Posts From My Groups/Societies","Posts By My Educators" };

    public ImageView imgFriends;
    public LinearLayout linearStartNewConversation;




    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
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

        View convertView = inflater.inflate(R.layout.fragment_home, container, false);
        tabs = (PagerSlidingTabStrip)convertView.findViewById(R.id.tabs);
        pager = (ViewPager)convertView.findViewById(R.id.pager);
        linearStartNewConversation = (LinearLayout)convertView.findViewById(R.id.linearStartNewConversation);

        return convertView;
    }


    public void setFriendsVisibility(boolean value){

        if(value == true){

            imgFriends.setVisibility(View.VISIBLE);
            linearStartNewConversation.setVisibility(View.GONE);

        }else{

            imgFriends.setVisibility(View.GONE);
            linearStartNewConversation.setVisibility(View.VISIBLE);

        }

    }

    public boolean getFriendsVisibility(){

        boolean isFriendsVisible = false;

        if(imgFriends.getVisibility() == View.VISIBLE){

            isFriendsVisible = true;

        }else{
            isFriendsVisible = false;
        }

        return  isFriendsVisible;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setAllSpinnerGone();
        adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);
        tabs.setShouldExpand(true);
        tabs.setIndicatorColor(getResources().getColor(R.color.app_color));
        //  tabs.setIndicatorColor(Color.TRANSPARENT);
        tabs.setBackgroundColor(Color.parseColor("#f5f5f5"));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        ChatFragment chatFragment = ChatFragment.newInstance("","");
        ft = fm.beginTransaction();
        ft.add(R.id.rightContainer,chatFragment,"chatView");
        ft.commit();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm ) {
            super(fm);

        }
        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

            return ChildCurrentActivityListFragment.newInstance("","");

        }
    }

}
