package com.example.user.sdm_onload_vehicle_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class Flash_page extends AppCompatActivity
{

    private static int SPLASH_TIME_OUT = 5000;
    Handler handler;
    SharedPrefHandler sharedPrefHandler;
    private Context ctx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_page);




        Thread thread=new Thread()
        {
            @Override
            public void run() {
                try {

                    sleep(5000);
                    sharedPrefHandler = new SharedPrefHandler(getApplicationContext());

                    Intent inwhizz_gcm = new Intent(Flash_page.this, Choose_mode.class);
                      startActivity(inwhizz_gcm);




//                    if (sharedPrefHandler.getSharedPreferences("login").equals("NF"))
//                    {
//                        sharedPrefHandler.setSharedPreferences("login", "false");
//                        Intent inwhizz_gcm = new Intent(Flash_page.this, Choose_mode.class);
//                        startActivity(inwhizz_gcm);
//
//                    }
//                    else if (sharedPrefHandler.getSharedPreferences("login").equals("false"))
//                    {
//                        Intent inwhizz_gcm = new Intent(Flash_page.this, Choose_mode.class);
//                        startActivity(inwhizz_gcm);
//
//                    }
//                    else if (sharedPrefHandler.getSharedPreferences("login").equals("true"))
//                    {
//                        Intent i = new Intent(getBaseContext(), MainActivity.class);
//                        startActivity(i);
//
//                    }
                    finish();
                }
                catch (Exception e)
                {

                }
            }
        };
        thread.start();


    }
}
