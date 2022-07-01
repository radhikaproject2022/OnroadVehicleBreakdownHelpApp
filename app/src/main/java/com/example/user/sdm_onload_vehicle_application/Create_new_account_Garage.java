package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Create_new_account_Garage extends AppCompatActivity
{
    EditText editText_name,editText_mno,editText_email,editText_password;
    Spinner spinner_city,spinner_occupation;
    Button button_submit;
    String stringname,stringmno,stringemail,stringaddress,stringpassword;
    SharedPrefHandler sharedPrefHandler;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String pattern = "[6-9][0-9]{10}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account__garage);


        sharedPrefHandler=new SharedPrefHandler(this);

        editText_name=(EditText)findViewById(R.id.create_account_username_garage);
        editText_mno=(EditText)findViewById(R.id.create_account_mno_garage);
        editText_email=(EditText)findViewById(R.id.create_account_address_garage);
        editText_password=(EditText)findViewById(R.id.create_account_password_garage);
        button_submit=(Button)findViewById(R.id.create_account_submit_garage);

        button_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                stringname = editText_name.getText().toString();
                stringmno = editText_mno.getText().toString();
                stringemail = editText_email.getText().toString();
                stringpassword = editText_password.getText().toString();

                sharedPrefHandler.setSharedPreferences("name", stringname);
                sharedPrefHandler.setSharedPreferences("mno", stringmno);
                sharedPrefHandler.setSharedPreferences("gmno", stringmno);
                sharedPrefHandler.setSharedPreferences("gpass", stringpassword);

                sharedPrefHandler.setSharedPreferences("email", stringemail);
                if (stringemail.isEmpty() || !stringemail.matches(emailPattern) ||  stringmno.length()<10 || stringmno.matches(pattern))
                {

                    Toast.makeText(Create_new_account_Garage.this, "Enter Valid Information", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    CreateUserAccount();

                    Intent intent = new Intent(getApplication(), Garage_login.class);
                    startActivity(intent);
                    Toast.makeText(Create_new_account_Garage.this, "Account Create Success", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.CreateAccountGarage(
                stringname,
                stringmno,
                stringemail,
                stringpassword
        );

        call.enqueue(new Callback<IsExist>() {
            @Override
            public void onResponse(Call<IsExist> call, Response<IsExist> response) {
                IsExist responseResult = response.body();

                Boolean isSuccess = false;
                if (responseResult != null) {
                    isSuccess = responseResult.getSuccess();
                }

                if (isSuccess) {



                } else {
                    // Show Creation Failed Message

                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
