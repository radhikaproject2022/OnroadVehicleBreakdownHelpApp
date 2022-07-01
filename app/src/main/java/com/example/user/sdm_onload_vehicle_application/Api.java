package com.example.user.sdm_onload_vehicle_application;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api
{
    // String BASE_URL = "https://simplifiedcoding.net/demos/";
    // String BASE_URL = "https://fanciful-petroleum.000webhostapp.com/api/";
    //String BASE_URL = "https://jolty-act.000webhostapp.com/onroad/user_api/";
    String BASE_URL = "http://192.168.43.183/onroad/";

    //http://192.168.43.183/onroad/user_api/garage_name.php


    @GET("login.php") //i.e https://fanciful-petroleum.000webhostapp.com/api/user/login?mobileNo=9035292096&password=123456
    Call<IsExist> doLogin(@Query("mobileNo") String username, @Query("password") String password);





    @GET("user_api/fetch_profile.php")
    Call<List<User>> getProductByCode(@Query("f1") String string_mno);

    @GET("fuel_api/fetch_Fuel_profile.php")
    Call<List<Fuel>> getFuel_profile(@Query("f1") String string_mno);



    @GET("garage_api/fetch_Garage_profile.php")
    Call<List<Garage_User>> getGarage_profile(@Query("f1") String string_mno);



    @GET("user_api/fetch_pg_details.php")
    Call<List<Garage>> getLoundryDetails(@Query("f1") String string_shop_name);



    @GET("user_api/fetch_pg_details_fuel.php")
    Call<List<FuelConstructor>> getLoundryDetailsFuel(@Query("f1") String string_shop_name);




    @GET("garage_api/fetch_get_shop_id.php")
    Call<List<Garage_enquiry>> getGarageId();

    @GET("fuel_api/fetch_get_shop_id.php")
    Call<List<Fuel_enquiry>> getFuelId();


    @GET("garage_api/fetch_get_shop_enquiry_problem.php")
    Call<List<Garage_enquiry>> getGarageProblem(@Query("f1") String string_input);

    @GET("fuel_api/fetch_get_shop_enquiry_problem.php")
    Call<List<Fuel_enquiry>> getFuelProblem(@Query("f1") String string_input);


    @GET("garage_api/fetch_get_shop_enquiry_problem_area_name.php")
    Call<List<Garage_enquiry>> Garage_user_address(@Query("f1") String string_input,@Query("f2") String string_input1);



    @GET("fuel_api/fetch_get_Fuel_enquiry_problem_area_name.php")
    Call<List<Fuel_enquiry>> Fuel_user_address(@Query("f1") String string_input,@Query("f2") String string_input1);




    @GET("garage_api/fetch_get_shop_enquiry_problem_details.php")
    Call<List<Garage_enquiry>> getUserProblemDetails(@Query("f1") String string_shop_name,@Query("f2") String string_input);


    @GET("fuel_api/fetch_get_shop_enquiry_problem_details.php")
    Call<List<Fuel_enquiry>> getUserProblemDetailsFuel(@Query("f1") String string_shop_name,@Query("f2") String string_input);





    @GET("user_api/fetch_area_name.php")
    Call<List<Garage>> getAreaName(@Query("f1") String string_input);


    @GET("user_api/fetch_area_name_fuel.php")
    Call<List<FuelConstructor>> getAreaNameFuel(@Query("f1") String string_input);





    @GET("user_api/fetch_city_name.php")
    Call<List<Garage>> getCityName();




    @GET("user_api/fetch_city_name_fuel.php")
    Call<List<FuelConstructor>> getCityNameFuel();




    @GET("user_api/garage_name.php")
    Call<List<Garage>> getShopName(@Query("f1") String string_input, @Query("f2") String string_input1);


    @GET("user_api/garage_name_fuel.php")
    Call<List<FuelConstructor>> getShopNameFuel(@Query("f1") String string_input, @Query("f2") String string_input1);




    @POST("garage_api/garage_register.php")
    Call<IsExist> CreateAccountGarage(
            @Query("f1") String stringname,
            @Query("f2") String stringmno,
            @Query("f3") String stringemail,
            @Query("f4") String stringpassword

    );
    @POST("user_api/register.php")
    Call<IsExist> CreateAccount(
            @Query("f1") String stringname,
            @Query("f2") String stringmno,
            @Query("f3") String stringemail,
            @Query("f4") String stringpassword

    );


    @POST("user_api/updaterating.php")
    Call<IsExist> UpdateRating(
            @Query("f1") String rating,
            @Query("f2") String string_sid
    );


//

    @POST("user_api/update_profile.php")
    Call<IsExist> UpdateAccount(
            @Query("f1") String stringname,
            @Query("f2") String stringmno,
            @Query("f3") String stringemail,
            @Query("f4") String stringpassword


    );
//
//
//
    @POST("user_api/user_enquiry.php")

    Call<IsExist> Enquiry_loundry(
            @Query("f1") String string_sid,
            @Query("f2") String string_name,
            @Query("f3") String string_mno,
            @Query("f4") String string_email,
            @Query("f5") String string_requirment,
            @Query("f6") String string_postal

    );



    @POST("user_api/user_enquiry_fuel.php")
    Call<IsExist> Enquiry_loundryfuel(
            @Query("f1") String string_sid,
            @Query("f2") String string_name,
            @Query("f3") String string_mno,
            @Query("f4") String string_email,
            @Query("f5") String string_requirment,
            @Query("f6") String string_postal

    );



    @POST("garage_api/garage_add_shop.php")
    Call<IsExist> GarageAddShop(
            @Query("f1") String string1,
            @Query("f2") String string2,
            @Query("f3") String string3,
            @Query("f4") String string4,
            @Query("f5") String string5,
            @Query("f6") String string6,
            @Query("f7") String string7,
            @Query("f8") String string8,
            @Query("f9") String string9,
            @Query("f10") String string10


    );

    @POST("fuel_api/Fuel_add_shop.php")
    Call<IsExist> FuelAddShop(
            @Query("f1") String string1,
            @Query("f2") String string2,
            @Query("f3") String string3,
            @Query("f4") String string4,
            @Query("f5") String string5,
            @Query("f6") String string6,
            @Query("f7") String string7,
            @Query("f8") String string8,
            @Query("f9") String string9,
            @Query("f10") String string10


    );

    @POST("fuel_api/Fuel_register.php")
    Call<IsExist> CreateAccountFuel(
            @Query("f1") String stringname,
            @Query("f2") String stringmno,
            @Query("f3") String stringemail,
            @Query("f4") String stringpassword

    );



}
