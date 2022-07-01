package com.example.user.sdm_onload_vehicle_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity
{
    EditText edt1,edt2,edt3,edt4;
    String subject,msg,name,phone,email;
    Button btn;

    private String atach;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        email = "radhikaproject2022@gmail.com";

        edt1 = (EditText)findViewById(R.id.editText_sub);
        edt2 = (EditText)findViewById(R.id.editText_name);
        edt3 = (EditText)findViewById(R.id.editText_msg);
        edt4 = (EditText)findViewById(R.id.editText_phone);
        btn = (Button)findViewById(R.id.btn_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subject = edt1.getText().toString();
                name = edt2.getText().toString();
                msg = edt3.getText().toString();
                phone = edt4.getText().toString();

                if(subject.length() > 0 && msg.length()  > 0 && name.length() > 0 ) {
                    if(phone.length() == 10)
                    {

                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"radhikaproject2022@gmail.com"});
                        //email.putExtra(Intent.EXTRA_CC, new String[]{ gfgcdwd@rediffmail.com});
                        //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        email.putExtra(Intent.EXTRA_TEXT, "Name : " + name + "\n" + "Phone : " + phone + "\n\n" + "Message : " + msg);

                        //need this to prompts email client only
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Choose an Email client :"));

                        edt1.setText("");
                        edt2.setText("");
                        edt3.setText("");
                        edt4.setText("");
                    }
                    else

                    {
                        Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Plese Fill All the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
