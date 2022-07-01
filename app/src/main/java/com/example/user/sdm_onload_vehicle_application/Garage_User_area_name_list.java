package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Garage_User_area_name_list extends AppCompatActivity {
    ListView listView_shop_list;
    String string_shop_list;

    String string_city;

    SharedPrefHandler sharedPrefHandler;
    ArrayAdapter<String> adapter;
    List<Garage_enquiry> productList;

    String[] products;
    String string_input,string_input1;

    EditText inputSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage__user_area_name_list);

        sharedPrefHandler=new SharedPrefHandler(this);

        string_input=sharedPrefHandler.getSharedPreferences("string_sid");
        string_input1=sharedPrefHandler.getSharedPreferences("problem_list");

        listView_shop_list=(ListView)findViewById(R.id.loundry_shop_list_garage);
        inputSearch = (EditText) findViewById(R.id.search_garage);

        getProducts(string_input,string_input1);
        listView_shop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                string_shop_list=listView_shop_list.getItemAtPosition(position).toString();
                sharedPrefHandler.setSharedPreferences("problem_area_name",string_shop_list);
                Intent intent=new Intent(getApplication(),Garage_proble_details.class);
                startActivity(intent);


            }
        });


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                Garage_User_area_name_list.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0)
            {
                // TODO Auto-generated method stub
            }
        });



    }




    private void getProducts(final String string_input, final String string_input1)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Garage_enquiry>> call = api.Garage_user_address(string_input, string_input1);

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

    private void loadProductListView()
    {
        //Creating an String array for the ListView
        products = new String[productList.size()];
        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++)
        {
            products[i] = productList.get(i).getstring_postal();
        }
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        listView_shop_list.setAdapter(adapter);
    }




}
