package com.example.user.sdm_onload_vehicle_application;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity
{

    Spinner spinner_city,spinner_city_fuel;
    Button button_submit,button_submit_fuel,button_payment,button5;
    String string_city;
    SharedPrefHandler sharedPrefHandler;
    ArrayAdapter<String> adapter;
    List<Garage> productList;
    String[] products;



    ArrayAdapter<String> adapterf;
    List<FuelConstructor> productListf;
    String[] productsf;


    Button button_profile,button_feedback,button_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefHandler=new SharedPrefHandler(this);


        spinner_city=(Spinner)findViewById(R.id.activity_main_city);
        button_submit=(Button)findViewById(R.id.activity_main_search);



        spinner_city_fuel=(Spinner)findViewById(R.id.activity_main_city_fuel);
        button_submit_fuel=(Button)findViewById(R.id.activity_main_search_fuel);




        button_profile=(Button)findViewById(R.id.activity_main_profile);
        button_feedback=(Button)findViewById(R.id.activity_main_feedback);
        button_about=(Button)findViewById(R.id.activity_main_about_us);

        button_payment=(Button)findViewById(R.id.activity_main_payment);

        button5=(Button)findViewById(R.id.activity_main_logout);

        getProducts();
        getProducts1();

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_city = spinner_city.getSelectedItem().toString();
                sharedPrefHandler.setSharedPreferences("city", string_city);
                Intent intent = new Intent(getApplication(), Area_name.class);
                startActivity(intent);

            }
        });


        button_submit_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                string_city=spinner_city_fuel.getSelectedItem().toString();
                sharedPrefHandler.setSharedPreferences("city",string_city);
                Intent intent=new Intent(getApplication(),Area_name_fuel.class);
                startActivity(intent);

            }
        });


        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Profile_page.class);
                startActivity(intent);

            }
        });




        button_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Feedback.class);
                startActivity(intent);

            }
        });





        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),About_us.class);
                startActivity(intent);

            }
        });




        button_payment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(getApplication(),Payment.class);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                sharedPrefHandler.setSharedPreferences("login", "false");
                sharedPrefHandler.editor.clear();
                Intent intent=new Intent(getApplication(),Login_page.class);
                startActivity(intent);
                finish();
            }
        });




    }


    private void getProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Garage>> call = api.getCityName();

        call.enqueue(new Callback<List<Garage>>() {
            @Override
            public void onResponse(Call<List<Garage>> call, Response<List<Garage>> response) {
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
            public void onFailure(Call<List<Garage>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView() {
        //Creating an String array for the ListView
        products = new String[productList.size()];

        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i).getpg_city();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        spinner_city.setAdapter(adapter);
    }






    //Fuel


    private void getProducts1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<FuelConstructor>> call = api.getCityNameFuel();

        call.enqueue(new Callback<List<FuelConstructor>>() {
            @Override
            public void onResponse(Call<List<FuelConstructor>> call, Response<List<FuelConstructor>> response) {
                // List<Product> productList = response.body();
                productListf = response.body();

                // Loading Products in Sagar style
                loadProductListView1();

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
            public void onFailure(Call<List<FuelConstructor>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView1() {
        //Creating an String array for the ListView
        productsf = new String[productListf.size()];

        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productListf.size(); i++) {
            productsf[i] = productListf.get(i).getpg_city();
        }

        adapterf = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, productsf);
        spinner_city_fuel.setAdapter(adapterf);
    }











}