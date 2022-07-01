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

public class Area_name_fuel extends AppCompatActivity {
    Spinner spinner_city;
    Button button_submit;
    String string_city;


    SharedPrefHandler sharedPrefHandler;

    ArrayAdapter<String> adapter;

    List<FuelConstructor> productList;


    // ArrayList for Listview
    // ArrayList<HashMap<String, String>> productList;

    // Listview Data
    String[] products;

    String string_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_name_fuel);



    sharedPrefHandler=new SharedPrefHandler(this);
    string_input=sharedPrefHandler.getSharedPreferences("city");
    spinner_city=(Spinner)findViewById(R.id.area_name_area_name_fuel);
    button_submit=(Button)findViewById(R.id.area_name_search_fuel);
    getProducts(string_input);

    button_submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            string_city=spinner_city.getSelectedItem().toString();

            sharedPrefHandler.setSharedPreferences("area_name",string_city);
            Intent intent=new Intent(getApplication(), Fuel_shop_list.class);
            startActivity(intent);

        }
    });


}



    private void getProducts(final String string_input) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<FuelConstructor>> call = api.getAreaNameFuel(string_input);

        call.enqueue(new Callback<List<FuelConstructor>>() {
            @Override
            public void onResponse(Call<List<FuelConstructor>> call, Response<List<FuelConstructor>> response) {
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
            public void onFailure(Call<List<FuelConstructor>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView() {
        //Creating an String array for the ListView
        products = new String[productList.size()];

        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i).getpg_area();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        spinner_city.setAdapter(adapter);
    }


}



