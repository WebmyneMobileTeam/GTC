package com.example.dhruvil.geleza_teacherconsole.CustomViews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dhruvil.geleza_teacherconsole.R;


/**
 * Created by dhruvil on 04-11-2014.
 */
public class CustomBadgeView extends LinearLayout{

    private ImageView img;
    private TextView txtName;
    private LinearLayout linear;
    private TextView badge;

    public CustomBadgeView(Context context) {
        super(context);
        this.setGravity(Gravity.CENTER);
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.item_custombadge,this, true);
        init();
    }

    private void init() {

        img = (ImageView)this.findViewById(R.id.imgBadge);
        txtName = (TextView)this.findViewById(R.id.txtBadgeName);
        linear = (LinearLayout)this.findViewById(R.id.linear);
        badge = (TextView)this.findViewById(R.id.txtBadgeValue);
    }

    public void setImage(int res){
        img.setImageResource(res);
    }

    public void setName(String name){
        txtName.setText(name);
    }

    public void setSelected(){
        linear.setBackgroundColor(Color.WHITE);
        img.setColorFilter(Color.parseColor("#cdcdcd"), PorterDuff.Mode.SRC_ATOP);
        txtName.setTextColor(Color.parseColor("#cdcdcd"));
        badge.setBackgroundResource(R.drawable.badge_selected);
    }

    public void setUnSelected(){

        linear.setBackgroundColor(getResources().getColor(R.color.background_color));
        img.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
        txtName.setTextColor(Color.parseColor("#000000"));
        badge.setBackgroundResource(R.drawable.badge_green);

    }

    public void clearBadge(){
       badge.setVisibility(View.GONE);
    }

    public void displayBadge(int value){

        if(!badge.isShown()){
            badge.setVisibility(View.VISIBLE);
        }

        badge.setText(""+value);


    }

    public void flip(){
        img.setRotationY(-180);
    }








}
