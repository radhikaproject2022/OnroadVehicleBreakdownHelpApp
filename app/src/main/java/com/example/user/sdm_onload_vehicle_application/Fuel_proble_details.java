package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.net.Uri;
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

public class Fuel_proble_details extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    SharedPrefHandler sharedPrefHandler;
    String string_shop_name,string_input;
    List<Fuel_enquiry> productList;
    Button button_enquiry;
    RatingBar ratingBar;

    Button fcall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_proble_details);


        sharedPrefHandler=new SharedPrefHandler(this);
        string_shop_name=sharedPrefHandler.getSharedPreferences("problem_area_name");
        string_input=sharedPrefHandler.getSharedPreferences("string_sid");

        textView1=(TextView)findViewById(R.id.shop_name_garage_problemf);
        textView2=(TextView)findViewById(R.id.shop_address_garage_problemf);
        textView3=(TextView)findViewById(R.id.shop_price_details_garage_problemf);
        textView4=(TextView)findViewById(R.id.shop_rating_garage_problemf);
        textView5=(TextView)findViewById(R.id.shop_work_details_garage_problemf);
        textView7=(TextView)findViewById(R.id.tv_id_garage_problemf);

        fcall=(Button)findViewById(R.id.fcall);

        fcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String callnum=sharedPrefHandler.getSharedPreferences("fcall");

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + callnum));//change the number
                startActivity(callIntent);

            }
        });


        getProductByCode(string_shop_name, string_input);



    }

    private void getProductByCode(final String string_shop_name,final String string_input)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Fuel_enquiry>> call = api.getUserProblemDetailsFuel(string_shop_name,string_input);

        call.enqueue(new Callback<List<Fuel_enquiry>>() {
            @Override
            public void onResponse(Call<List<Fuel_enquiry>> call, Response<List<Fuel_enquiry>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if (response.body() != null) {
                    isSuccess = true;
                }

                if (isSuccess)
                {
                    textView1.setText(productList.get(0).getstring_sid());
                    textView2.setText("USER NAME : " + productList.get(0).getstring_name());
                    textView3.setText(" NUMBER : " + productList.get(0).getstring_mno());
                    textView4.setText("PROBLEM : " + productList.get(0).getstring_requirment());
                    textView5.setText("ADDRESS : " + productList.get(0).getstring_postal());

                    sharedPrefHandler.setSharedPreferences("fcall",productList.get(0).getstring_mno());

                    //finish();

                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<List<Fuel_enquiry>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
