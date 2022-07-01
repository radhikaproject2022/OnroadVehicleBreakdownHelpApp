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

public class Garage_problem_list extends AppCompatActivity {
    Spinner spinner_city;
    Button button_submit;
    String string_city;


    SharedPrefHandler sharedPrefHandler;

    ArrayAdapter<String> adapter;

    List<Garage_enquiry> productList;


    // ArrayList for Listview
    // ArrayList<HashMap<String, String>> productList;

    // Listview Data
    String[] products;

    String string_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_problem_list);

        sharedPrefHandler=new SharedPrefHandler(this);
        string_input=sharedPrefHandler.getSharedPreferences("string_sid");
        spinner_city=(Spinner)findViewById(R.id.garage_problem_list);
        button_submit=(Button)findViewById(R.id.garage_problem_list_search);
        getProducts(string_input);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                string_city=spinner_city.getSelectedItem().toString();

                sharedPrefHandler.setSharedPreferences("problem_list",string_city);
                Intent intent=new Intent(getApplication(), Garage_User_area_name_list.class);
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

        Call<List<Garage_enquiry>> call = api.getGarageProblem(string_input);

        call.enqueue(new Callback<List<Garage_enquiry>>() {
            @Override
            public void onResponse(Call<List<Garage_enquiry>> call, Response<List<Garage_enquiry>> response) {
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
            public void onFailure(Call<List<Garage_enquiry>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView() {
        //Creating an String array for the ListView
        products = new String[productList.size()];

        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i).getstring_requirment();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        spinner_city.setAdapter(adapter);
    }


}
