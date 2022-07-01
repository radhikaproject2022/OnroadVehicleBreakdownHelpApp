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

public class Fuel_login extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    TextView textView_create;
    SharedPrefHandler sharedPrefHandler;
ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_login);


        sharedPrefHandler=new SharedPrefHandler(this);

        usernameEditText = (EditText)findViewById(R.id.activity_main_usernameEditText_fuel);
        passwordEditText = (EditText)findViewById(R.id.activity_main_passwordEditText_fuel);
        loginButton = (Button)findViewById(R.id.activity_main_loginButton_fuel);


        textView_create = (TextView)findViewById(R.id.login_create_new_fuel);
//
//
//
        textView_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplication(),Create_new_account_fuel.class);
                startActivity(intent);

            }
        });
//


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                sharedPrefHandler.setSharedPreferences("mno",username);

                String s_mno=sharedPrefHandler.getSharedPreferences("fmno");
                String s_pass=sharedPrefHandler.getSharedPreferences("fpass");


                //validate form
                if(s_mno.equals(username) && s_pass.equals(password))
                {
                    //do login
                    //doLogin(username, password);
//
                    sharedPrefHandler.setSharedPreferences("user", username);
                    sharedPrefHandler.setSharedPreferences("login","true");
                    Intent intent=new Intent(getApplication(),MainActivity_Fuel.class);
                    startActivity(intent);
                    Toast.makeText(Fuel_login.this, "Login  Success", Toast.LENGTH_SHORT).show();

                    finish();

                }
                else
                {
                    Toast.makeText(Fuel_login.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean validateLogin(final String username, final String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_LONG).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}


