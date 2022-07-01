package com.example.user.sdm_onload_vehicle_application;

class Garage_User {
//    @SerializedName("product_name")

    private String stringname;
    private String stringmno;
    private String stringemail;
    private String stringpassword;




    public Garage_User(String stringname, String stringmno, String stringemail, String stringpassword) {

        this.stringname = stringname;
        this.stringmno = stringmno;
        this.stringemail = stringemail;
        this.stringpassword = stringpassword;







    }







    public String getstringname() {
        return stringname;
    }

    public void setstringname(String stringname) {
        this.stringname = stringname;
    }




    public String getstringmno() {
        return stringmno;
    }

    public void setstringmno(String stringmno) {
        this.stringmno = stringmno;
    }

    public String getstringemail() {
        return stringemail;
    }

    public void setstringemail(String stringemail) {
        this.stringemail = stringemail;
    }










    public String getstringpassword() {
        return stringpassword;
    }

    public void setstringpassword(String stringpassword) {
        this.stringpassword = stringpassword;
    }






}
