package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_Fuel extends AppCompatActivity {
    Spinner spinner_city;
    Button button_submit, button_payment, button5;
    String string_city;
    SharedPrefHandler sharedPrefHandler;
    ArrayAdapter<String> adapter;
    List<Fuel_enquiry> productList;
    String[] products;


    Button button_profile, button_feedback, button_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity__fuel);
        sharedPrefHandler = new SharedPrefHandler(this);


        spinner_city = (Spinner) findViewById(R.id.activity_main_area_fuel);
        button_submit = (Button) findViewById(R.id.activity_main_search_fuel);

        button_profile = (Button) findViewById(R.id.activity_main_profile_fuel);
        button_feedback = (Button) findViewById(R.id.activity_main_feedback_fuel);
        button_about = (Button) findViewById(R.id.activity_main_about_us_fuel);

        button_payment = (Button) findViewById(R.id.activity_main_add_shop_fuel);

        getProducts();

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_city = spinner_city.getSelectedItem().toString();
                sharedPrefHandler.setSharedPreferences("string_sid", string_city);
                Intent intent = new Intent(getApplication(), Fuel_problem_list.class);
                startActivity(intent);

            }
        });


        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), Profile_page_Fuel.class);
                startActivity(intent);

            }
        });


        button_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), Feedback.class);
                startActivity(intent);

            }
        });


        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), About_us.class);
                startActivity(intent);

            }
        });


        button_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), Add_shop_details_fuel.class);
                startActivity(intent);

            }
        });


    }


    private void getProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Fuel_enquiry>> call = api.getFuelId();

        call.enqueue(new Callback<List<Fuel_enquiry>>() {
            @Override
            public void onResponse(Call<List<Fuel_enquiry>> call, Response<List<Fuel_enquiry>> response) {
                // List<Product> productList = response.body();
                productList = response.body();

                // Loading Products in Sagar style
                loadProductListView();

                /*
                //Creating an String array for the ListView
                products = new String[productList.size()];

                //looping through all the products and inserting the names inside the string array
                for (int i = 0; i < productList.size(); i++) {
                    products[i] = productList.get(i).getProduct_name();
                }

                //displaying the string array into listview
                lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, products));
                 */
            }

            @Override
            public void onFailure(Call<List<Fuel_enquiry>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView() {
        //Creating an String array for the ListView
        products = new String[productList.size()];

        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i).getstring_sid();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        spinner_city.setAdapter(adapter);
    }
}