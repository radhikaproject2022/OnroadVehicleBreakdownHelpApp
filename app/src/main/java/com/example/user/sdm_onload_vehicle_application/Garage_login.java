package com.example.user.sdm_onload_vehicle_application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Garage_login extends AppCompatActivity
{

    Button button1,button2;

SharedPrefHandler sharedPrefHandler;

    String string_mno,string_pass;

    EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_login);

        sharedPrefHandler=new SharedPrefHandler(this);

        string_mno=sharedPrefHandler.getSharedPreferences("gmno");
        string_pass=sharedPrefHandler.getSharedPreferences("gpass");

        editText1=(EditText)findViewById(R.id.activity_main_usernameEditText_garagegg);
        editText2=(EditText)findViewById(R.id.activity_main_passwordEditText_garegegg);


        button1=(Button)findViewById(R.id.activity_main_loginButton_garegegg);
        button2=(Button)findViewById(R.id.garage_new_account);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gmno=editText1.getText().toString();
                String gpass=editText2.getText().toString();
                if (gmno.equals(string_mno) && gpass.equals(string_pass)) {

                    Intent intent = new Intent(getApplication(), MainActivity_garage.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Garage_login.this, "Login Success", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Garage_login.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(),Create_new_account_Garage.class);
                startActivity(intent);
                finish();
            }
        });

    }


}


