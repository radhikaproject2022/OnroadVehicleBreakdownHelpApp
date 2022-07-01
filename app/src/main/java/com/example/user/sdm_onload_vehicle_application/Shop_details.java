package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Shop_details extends AppCompatActivity
{
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    SharedPrefHandler sharedPrefHandler;
    String string_shop_name;
    List<Garage> productList;
    Button button_enquiry;
    RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garage_details);

        sharedPrefHandler=new SharedPrefHandler(this);
        string_shop_name=sharedPrefHandler.getSharedPreferences("shop_name");

        textView1=(TextView)findViewById(R.id.shop_name);
        textView2=(TextView)findViewById(R.id.shop_address);
        textView3=(TextView)findViewById(R.id.shop_price_details);
        textView4=(TextView)findViewById(R.id.shop_rating);
        textView5=(TextView)findViewById(R.id.shop_work_details);
        textView7=(TextView)findViewById(R.id.tv_id);

        ratingBar=(RatingBar)findViewById(R.id.ratingBardisplay);

        button_enquiry=(Button)findViewById(R.id.enquiry);


        button_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String s_id=textView1.getText().toString();

                sharedPrefHandler.setSharedPreferences("sid",s_id);


                Intent intent=new Intent(getApplication(), Enquiry_garage.class);
                startActivity(intent);
            }
        });


        getProductByCode(string_shop_name);



    }

    private void getProductByCode(final String string_shop_name)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Garage>> call = api.getLoundryDetails(string_shop_name);

        call.enqueue(new Callback<List<Garage>>() {
            @Override
            public void onResponse(Call<List<Garage>> call, Response<List<Garage>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if(response.body() != null) {
                    isSuccess = true;
                }

                if(isSuccess) {
                    textView1.setText(productList.get(0).getpgid());
                    textView2.setText("SHOP NAME : "+productList.get(0).getpg_name());
                    textView3.setText("SHOP NUMBER : "+productList.get(0).getpg_mno());
                    textView4.setText("SHOP ADDRESS : "+productList.get(0).getpg_address());
                    textView5.setText("SHOP WORK : "+productList.get(0).getpg_price());
                    textView7.setText("SERVICE CHARGE  :  "+productList.get(0).getPg_facilities());






                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Garage>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
