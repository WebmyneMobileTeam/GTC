package com.example.dhruvil.geleza_teacherconsole.Application;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dhruvil.geleza_teacherconsole.CustomViews.CustomBadgeView;
import com.example.dhruvil.geleza_teacherconsole.CustomViews.CustomTabView;
import com.example.dhruvil.geleza_teacherconsole.MyLearners.FragmentMyLearners;
import com.example.dhruvil.geleza_teacherconsole.R;
import com.example.dhruvil.geleza_teacherconsole.Tools.FragmentTools;
import com.example.dhruvil.geleza_teacherconsole.UploadItems.FragmentUploadItems;
import com.example.dhruvil.geleza_teacherconsole.UploadResults.FragmentUploadResults;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends FragmentActivity {

    private FrameLayout actionStrip;
    private CustomBadgeView btnMyGroups;
    private CustomBadgeView btnMessages;
    private CustomBadgeView btnRequests;

    private LinearLayout linearSideStudyMaterial;
    public  String[] subject_names = {"Home","My Learners","Upload results",
            "Upload Items","Tools"};

    private int[] subject_icons = {R.drawable.ic_home,R.drawable.ic_my_learners,R.drawable.ic_my_progress_record,
            R.drawable.ic_upload_items,R.drawable.ic_resources};
    private ArrayList<Fragment> tab_fragments;

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    private LinearLayout spinner1;
    private LinearLayout spinner2;
    private LinearLayout spinner3;

    private Spinner sp1;
    private Spinner sp2;
    private Spinner sp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionStrip = (FrameLayout)findViewById(R.id.actionStrip);
        linearSideStudyMaterial = (LinearLayout)findViewById(R.id.linearsideleft);

        txt1 = (TextView)findViewById(R.id.txtSpinnerOne);
        txt2 = (TextView)findViewById(R.id.txtSpinnerTwo);
        txt3 = (TextView)findViewById(R.id.txtSpinnerThree);

        spinner1 = (LinearLayout)findViewById(R.id.spinnerOne);
        spinner2 = (LinearLayout)findViewById(R.id.spinnerTwo);
        spinner3 = (LinearLayout)findViewById(R.id.spinnerThree);

        sp1 = (Spinner)findViewById(R.id.sp1);
        sp2 = (Spinner)findViewById(R.id.sp2);
        sp3 = (Spinner)findViewById(R.id.sp3);




        tab_fragments = new ArrayList<Fragment>();

        tab_fragments.add(HomeFragment.newInstance("",""));
        tab_fragments.add(FragmentMyLearners.newInstance("",""));
        tab_fragments.add(FragmentUploadResults.newInstance("",""));
        tab_fragments.add(FragmentUploadItems.newInstance("",""));
        tab_fragments.add(FragmentTools.newInstance("",""));

        insertStripOptions();
        fillupVerticalTabs();
        selectSubject(0);
        setAllSpinnerGone();
    }


    public void setAllSpinnerGone(){

        spinner1.setVisibility(View.INVISIBLE);
        spinner2.setVisibility(View.INVISIBLE);
        spinner3.setVisibility(View.INVISIBLE);


    }

    public void setMyLearnerStrips(){

        spinner1.setVisibility(View.VISIBLE);
        spinner2.setVisibility(View.VISIBLE);
        spinner3.setVisibility(View.INVISIBLE);

        txt1.setText("Class/Grade");
        txt2.setText("Subject");

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.class_grade_array));
        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.subject_array));

        sp1.setAdapter(adapter_class);
        sp2.setAdapter(adapter_subject);


    }


    public void setUploadResultsStrips(){

        spinner1.setVisibility(View.VISIBLE);
        spinner2.setVisibility(View.INVISIBLE);
        spinner3.setVisibility(View.INVISIBLE);

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.class_grade_array));

        sp1.setAdapter(adapter_class);



    }

    public void setUploadItemsStrips(){

        spinner1.setVisibility(View.VISIBLE);
        spinner2.setVisibility(View.VISIBLE);
        spinner3.setVisibility(View.VISIBLE);

        txt1.setText("Select Subject");
        txt2.setText("Item Type");
        txt3.setText("For Grade/Class");

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.class_grade_array));
        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.subject_array));

        sp1.setAdapter(adapter_subject);
        sp2.setAdapter(adapter_subject);
        sp3.setAdapter(adapter_class);


    }







    private void fillupVerticalTabs() {

        for(int i=0;i<subject_names.length;i++){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            CustomTabView tabView = new CustomTabView(MainActivity.this);
            tabView.setImage(subject_icons[i]);
            tabView.setName(subject_names[i]);
            tabView.clearBadge();
            tabView.resizeImage(150,185);

            if(i == 2){
                tabView.displayBadge(2);
            }

            tabView.setOnClickListener(mySubjectClick);
            linearSideStudyMaterial.addView(tabView, params);
            linearSideStudyMaterial.invalidate();

        }

    }

    private View.OnClickListener mySubjectClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            CustomTabView tabV = (CustomTabView)v;
            int index = linearSideStudyMaterial.indexOfChild(tabV);
            selectSubject(index);

        }
    };

    private void selectSubject(int index) {

        for(int i=0;i< linearSideStudyMaterial.getChildCount();i++){

            CustomTabView customTabView = (CustomTabView) linearSideStudyMaterial.getChildAt(i);
            if(index == i){
                customTabView.setSelected();
                changeFragment(i);
            }else{
                customTabView.setUnSelected();
            }
        }
    }

    private void changeFragment(int i) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container,tab_fragments.get(i), "hi");
        ft.commit();

    }

    private void insertStripOptions() {

        FrameLayout.LayoutParams paramsFrame = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsFrame.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        paramsFrame.rightMargin = 32;

        LinearLayout lineatActionStrip = new LinearLayout(MainActivity.this);
        lineatActionStrip.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams paramsLinear = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        btnRequests = new CustomBadgeView(MainActivity.this);
        btnRequests.setImage(R.drawable.ic_requests);
        btnRequests.clearBadge();
        btnRequests.setName("Requests");

        btnMessages = new CustomBadgeView(MainActivity.this);
        btnMessages.setImage(R.drawable.ic_messages);
        btnMessages.clearBadge();
        btnMessages.setName("Messages");

        btnMyGroups = new CustomBadgeView(MainActivity.this);
        btnMyGroups.setImage(R.drawable.ic_social_group);
        btnMyGroups.clearBadge();
        btnMyGroups.setName("Calender/Events");

        lineatActionStrip.addView(btnMyGroups,paramsLinear);

        paramsLinear.leftMargin = 22;
        lineatActionStrip.addView(btnMessages,paramsLinear);
        lineatActionStrip.addView(btnRequests,paramsLinear);
        actionStrip.addView(lineatActionStrip,paramsFrame);

        new CountDownTimer(2500, 1000) {

            @Override
            public void onFinish() {

                btnMyGroups.displayBadge(2);
                btnMessages.displayBadge(6);
                btnRequests.displayBadge(1);
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
