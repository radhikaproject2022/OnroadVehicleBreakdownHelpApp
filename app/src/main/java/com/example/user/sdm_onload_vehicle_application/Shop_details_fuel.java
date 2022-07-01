package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class Shop_details_fuel extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    SharedPrefHandler sharedPrefHandler;
    String string_shop_name;
    List<FuelConstructor> productList;
    Button button_enquiry;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details_fuel);
        sharedPrefHandler=new SharedPrefHandler(this);
        string_shop_name=sharedPrefHandler.getSharedPreferences("shop_name");

        textView1=(TextView)findViewById(R.id.shop_namef);
        textView2=(TextView)findViewById(R.id.shop_addressf);
        textView3=(TextView)findViewById(R.id.shop_price_detailsf);
        textView4=(TextView)findViewById(R.id.shop_ratingf);
        textView5=(TextView)findViewById(R.id.shop_work_detailsf);
        textView7=(TextView)findViewById(R.id.tv_idf);

        ratingBar=(RatingBar)findViewById(R.id.ratingBardisplayf);

        button_enquiry=(Button)findViewById(R.id.enquiryf);


        button_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String s_id=textView1.getText().toString();

                sharedPrefHandler.setSharedPreferences("sid",s_id);


                Intent intent=new Intent(getApplication(), Enquiry_garage_fuel.class);
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

        Call<List<FuelConstructor>> call = api.getLoundryDetailsFuel(string_shop_name);

        call.enqueue(new Callback<List<FuelConstructor>>() {
            @Override
            public void onResponse(Call<List<FuelConstructor>> call, Response<List<FuelConstructor>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if (response.body() != null) {
                    isSuccess = true;
                }

                if (isSuccess) {
                    textView1.setText(productList.get(0).getpgid());
                    textView2.setText("SHOP NAME : " + productList.get(0).getpg_name());
                    textView3.setText("SHOP NUMBER : " + productList.get(0).getpg_mno());
                    textView4.setText("SHOP ADDRESS : " + productList.get(0).getpg_address());
                    textView5.setText("SHOP WORK : " + productList.get(0).getpg_price());
                    textView7.setText("SERVICE CHARGE  :  " + productList.get(0).getPg_facilities());


                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<FuelConstructor>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
