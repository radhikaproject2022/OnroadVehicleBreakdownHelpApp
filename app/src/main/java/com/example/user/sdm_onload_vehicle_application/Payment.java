package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Payment  extends AppCompatActivity
{

    Button button_gpay,button_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        button_gpay=(Button)findViewById(R.id.gpay);
        button_phone=(Button)findViewById(R.id.phone_pay);


        button_gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.nbu.paisa.user");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(Payment.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                }

            }
        });



        button_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.phonepe.app");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(Payment.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
