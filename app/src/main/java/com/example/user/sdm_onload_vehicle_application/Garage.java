package com.example.user.sdm_onload_vehicle_application;

class Garage {
//    @SerializedName("product_name")

    private String shop_id;
    private String shop_name;
    private String mno;
    private String address;


    private String shop_work;
    private String service_charge;
    private String owner_name;

    private String city_name;
    private String area_name;






    public Garage(String shop_id, String shop_name, String mno, String address, String shop_work, String service_charge, String owner_name, String city_name, String area_name) {

        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.mno = mno;
        this.address = address;
        this.shop_work = shop_work;
        this.service_charge = service_charge;
        this.owner_name = owner_name;


        this.city_name = city_name;
        this.area_name = area_name;




    }







    public String getpgid() {
        return shop_id;
    }

    public void setshop_id(String shop_id) {
        this.shop_id = shop_id;
    }




    public String getpg_name() {
        return shop_name;
    }

    public void setshop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getpg_mno() {
        return mno;
    }

    public void setemail(String mno) {
        this.mno = mno;
    }


    public String getpg_address() {
        return address;
    }

    public void setmobile(String address) {
        this.address = address;
    }




    public String getpg_price() {
        return shop_work;
    }

    public void setcity_name(String shop_work) {
        this.shop_work = shop_work;
    }






    public String getPg_facilities() {
        return service_charge;
    }

    public void setarea_name(String service_charge) {
        this.service_charge = service_charge;
    }





    public String getpg_owner_name() {
        return owner_name;
    }

    public void setpostal_address(String owner_name) {
        this.owner_name = owner_name;
    }





    public String getpg_city() {
        return city_name;
    }

    public void setpg_city(String city_name) {
        this.city_name = city_name;
    }



    public String getpg_area() {
        return area_name	;
    }

    public void setpg_area(String area_name	) {
        this.area_name	 = area_name;
    }

}
