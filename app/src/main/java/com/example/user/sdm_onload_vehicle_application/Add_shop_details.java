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

public class Add_shop_details extends AppCompatActivity {
    EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10;
    Spinner spinner_city,spinner_occupation;
    Button button_submit;
    String string1,string2,string3,string4,string5,string6,string7,string8,string9,string10;
    SharedPrefHandler sharedPrefHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_details);




        sharedPrefHandler=new SharedPrefHandler(this);

        editText1=(EditText)findViewById(R.id.shop_id);
        editText2=(EditText)findViewById(R.id.shop_name);
        editText3=(EditText)findViewById(R.id.mno);
        editText4=(EditText)findViewById(R.id.email);

        editText5=(EditText)findViewById(R.id.city_name);
        editText6=(EditText)findViewById(R.id.area_name);
        editText7=(EditText)findViewById(R.id.address);
        editText8=(EditText)findViewById(R.id.shop_work);

        editText9=(EditText)findViewById(R.id.service_charge);
        editText10=(EditText)findViewById(R.id.owner_name);

        button_submit=(Button)findViewById(R.id.add_shop_submit);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string1 = editText1.getText().toString();
                string2 = editText2.getText().toString();
                string3 = editText3.getText().toString();
                string4 = editText4.getText().toString();
                string5 = editText5.getText().toString();
                string6 = editText6.getText().toString();
                string7 = editText7.getText().toString();
                string8 = editText8.getText().toString();
                string9 = editText9.getText().toString();
                string10 = editText10.getText().toString();



                CreateUserAccount();

                Intent intent = new Intent(getApplication(), MainActivity_garage.class);
                startActivity(intent);
                Toast.makeText(Add_shop_details.this, "Added Success", Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.GarageAddShop(
                string1,string2,string3,string4,string5,string6,string7,string8,string9,string10
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
