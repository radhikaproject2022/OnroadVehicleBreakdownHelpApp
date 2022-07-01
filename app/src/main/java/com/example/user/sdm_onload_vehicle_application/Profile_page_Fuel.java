package com.example.user.sdm_onload_vehicle_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile_page_Fuel extends AppCompatActivity {
    TextView editText_name, editText_mno, editText_email, editText_password;
    Button button_submit;
    String stringname, stringmno, stringemail, stringaddress, stringpassword;
    SharedPrefHandler sharedPrefHandler;
    List<Fuel> productList;
    String string_mno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page__fuel);
        sharedPrefHandler = new SharedPrefHandler(this);

        string_mno = sharedPrefHandler.getSharedPreferences("mno");
        editText_name = (TextView) findViewById(R.id.create_account_usernameu_fuel);
        editText_mno = (TextView) findViewById(R.id.create_account_mnou_fuel);
        editText_email = (TextView) findViewById(R.id.create_account_addressu_fuel);
        editText_password = (TextView) findViewById(R.id.create_account_passwordu_fuel);


        getProductByCode(string_mno);


    }


    private void getProductByCode(final String string_mno) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Fuel>> call = api.getFuel_profile(string_mno);

        call.enqueue(new Callback<List<Fuel>>() {
            @Override
            public void onResponse(Call<List<Fuel>> call, Response<List<Fuel>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if (response.body() != null) {
                    isSuccess = true;
                }

                if (isSuccess) {
                    editText_name.setText(productList.get(0).getstringname());
                    editText_mno.setText(productList.get(0).getstringmno());

                    editText_email.setText(productList.get(0).getstringemail());
                    editText_password.setText(productList.get(0).getstringpassword());


                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Fuel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}