package com.example.user.sdm_onload_vehicle_application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_page extends AppCompatActivity
{
      EditText usernameEditText;
      EditText passwordEditText;
      Button loginButton;

    SharedPrefHandler sharedPrefHandler;
TextView textView_create;
    ProgressDialog progressBar;

    String string_umno,string_upass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        sharedPrefHandler=new SharedPrefHandler(this);

        usernameEditText = (EditText)findViewById(R.id.activity_main_usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.activity_main_passwordEditText);
        loginButton = (Button)findViewById(R.id.activity_main_loginButton);


        textView_create = (TextView)findViewById(R.id.login_create_new);



        textView_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplication(),Create_new_account.class);
                startActivity(intent);

            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                sharedPrefHandler.setSharedPreferences("mno",username);

                string_umno=sharedPrefHandler.getSharedPreferences("umno");
                string_upass=sharedPrefHandler.getSharedPreferences("upass");



                //validate form
                if(string_umno.equals(username) && string_upass.equals(password))
                {
                    //do login
                    //doLogin(username, password);
//
                    sharedPrefHandler.setSharedPreferences("user", username);
                    sharedPrefHandler.setSharedPreferences("login","true");
                    Intent intent=new Intent(getApplication(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Login_page.this, "Login  Success", Toast.LENGTH_SHORT).show();

                    finish();

                }
                else
                {
                    Toast.makeText(Login_page.this, "Login  Fail", Toast.LENGTH_SHORT).show();

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


//    private void doLogin(final String username, final String password){
//        // creating progress bar dialog
//        progressBar = new ProgressDialog(this);
//        progressBar.setCancelable(false);
//        progressBar.setMessage("Please wait...");
//        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        // progressBar.setProgress(0);
//        // progressBar.setMax(100);
//        progressBar.show();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
//                .build();
//
//        Api api = retrofit.create(Api.class);
//
//        Call<IsExist> call = api.doLogin(username, password);
//
//        call.enqueue(new Callback<IsExist>() {
//            @Override
//            public void onResponse(Call<IsExist> call, Response<IsExist> response) {
//                IsExist responseResult = response.body();
//
//                Boolean isSuccess = false;
//                if(responseResult != null) {
//                    isSuccess = responseResult.getSuccess();
//                }
//
//                if(isSuccess) {
//                    progressBar.dismiss();
//
//
//
//                } else {
//                    // Show Login Failed Message
//                    progressBar.dismiss();
//                    showLoginFailedToast();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<IsExist> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void showLoginFailedToast() {
//        Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();
//    }


}
