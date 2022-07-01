package com.example.user.sdm_onload_vehicle_application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class Profile_page extends AppCompatActivity
{
    TextView editText_name,editText_mno,editText_email,editText_password;
    Button button_submit;
    String stringname,stringmno,stringemail,stringaddress,stringpassword;
    SharedPrefHandler sharedPrefHandler;
    List<User> productList;
    String string_mno;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        sharedPrefHandler=new SharedPrefHandler(this);

        string_mno=sharedPrefHandler.getSharedPreferences("mno");
        editText_name=(TextView)findViewById(R.id.create_account_usernameu);
        editText_mno=(TextView)findViewById(R.id.create_account_mnou);
        editText_email=(TextView)findViewById(R.id.create_account_addressu);
        editText_password=(TextView)findViewById(R.id.create_account_passwordu);
        button_submit=(Button)findViewById(R.id.create_account_submitu);

        getProductByCode(string_mno);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                stringname=editText_name.getText().toString();
                stringmno=editText_mno.getText().toString();
                stringemail=editText_email.getText().toString();
                stringpassword=editText_password.getText().toString();




                UpdateAccount();

            }
        });
    }



    private void getProductByCode(final String string_mno)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<User>> call = api.getProductByCode(string_mno);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if(response.body() != null) {
                    isSuccess = true;
                }

                if(isSuccess) {
                    editText_name.setText(productList.get(0).getstringname());
                    editText_mno.setText(productList.get(0).getstringmno());

                    editText_email.setText(productList.get(0).getstringemail());
                    editText_password.setText(productList.get(0).getstringpassword());


                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void UpdateAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.UpdateAccount(
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
                if(responseResult != null) {
                    isSuccess = responseResult.getSuccess();
                }

                if(isSuccess) {
                    showEditSuccessToast();

                } else {
                    // Show Creation Failed Message
                    showCreateFailedToast();
                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showCreateFailedToast() {
        Toast.makeText(this, "OOPS,   Create action failed!", Toast.LENGTH_LONG).show();
    }

    private void showCreateSuccessToast() {
        Toast.makeText(this, "  created successfully.", Toast.LENGTH_LONG).show();
    }

    private void showEditFailedToast() {
        Toast.makeText(this, "OOPS,   Edit action failed!", Toast.LENGTH_LONG).show();
    }

    private void showEditSuccessToast() {
        Toast.makeText(this, "  updated successfully.", Toast.LENGTH_LONG).show();
    }

}
