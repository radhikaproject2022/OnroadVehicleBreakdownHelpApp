package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Choose_mode extends AppCompatActivity
{
    Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);


    button1=(Button)findViewById(R.id.btn_im_garage);
        button2=(Button)findViewById(R.id.btn_im_user);
        button3=(Button)findViewById(R.id.btn_im_fuel);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(),Garage_login.class);
                startActivity(intent);

            }

            });




        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(),Login_page.class);
                startActivity(intent);

            }

        });




        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(),Fuel_login.class);
                startActivity(intent);

            }

        });


    }


}
