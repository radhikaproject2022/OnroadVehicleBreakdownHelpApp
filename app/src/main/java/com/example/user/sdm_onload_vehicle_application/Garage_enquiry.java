package com.example.user.sdm_onload_vehicle_application;

class Garage_enquiry {
//    @SerializedName("product_name")

    private String string_sid;
    private String string_name;
    private String string_mno;
    private String string_email;


    private String string_requirment;
    private String string_postal;







    public Garage_enquiry(String string_sid, String string_name, String string_mno, String string_email, String string_requirment, String string_postal)
    {

        this.string_sid = string_sid;
        this.string_name = string_name;
        this.string_mno = string_mno;
        this.string_email = string_email;
        this.string_requirment = string_requirment;
        this.string_postal = string_postal;





    }







    public String getstring_sid() {
        return string_sid;
    }






    public String getstring_name() {
        return string_name;
    }



    public String getstring_mno() {
        return string_mno;
    }



    public String getstring_email() {
        return string_email;
    }






    public String getstring_requirment() {
        return string_requirment;
    }








    public String getstring_postal() {
        return string_postal;
    }


}
