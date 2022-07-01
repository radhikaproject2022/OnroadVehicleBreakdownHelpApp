package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Enquiry_garage_fuel extends AppCompatActivity {
    EditText editText_requirment,editText_address;
    SharedPrefHandler sharedPrefHandler;
    String string_sid,string_name,string_mno,string_email,string_requirment,string_postal;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_garage_fuel);
        sharedPrefHandler=new SharedPrefHandler(this);

        string_sid=sharedPrefHandler.getSharedPreferences("sid");
        string_name=sharedPrefHandler.getSharedPreferences("name");
        string_mno=sharedPrefHandler.getSharedPreferences("mno");
        string_email=sharedPrefHandler.getSharedPreferences("email");

        editText_requirment=(EditText)findViewById(R.id.requirmentfuel);
        editText_address=(EditText)findViewById(R.id.postalfuel);

        button=(Button)findViewById(R.id.subfuel);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                string_requirment=editText_requirment.getText().toString();
                string_postal=editText_address.getText().toString();

                CreateUserAccount();

                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.Enquiry_loundryfuel(
                string_sid,
                string_name,
                string_mno,
                string_email,
                string_requirment,
                string_postal
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
