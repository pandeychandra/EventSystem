package com.example.moonlight.eventsystemdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterationActivity extends AppCompatActivity {
    EditText name, email, password, rePassword;
    String user,email1,password1,repassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        rePassword=(EditText)findViewById(R.id.cpassword);
    }

    public void userClick(View view) {

        user = name.getText().toString();
        email1 = email.getText().toString();
         password1 = password.getText().toString();
        repassword = rePassword.getText().toString();
        register();
        }



       /* RegisterLoginWithMysql cc = new RegisterLoginWithMysql(this);
       cc.execute(method,user,email1,password1,repassword);
*/

    private void register() {
        String method = "register";


        if(!validate())
        {
            Toast.makeText(getApplicationContext(),"SignUp Failed",Toast.LENGTH_SHORT).show();
        }else {

            RegisterLoginWithMysql cc = new RegisterLoginWithMysql(this);
            cc.execute(method,user,email1,password1,repassword);


        }
    }
    private boolean validate() {
        boolean valid=true;
        if(user.isEmpty()||user.length()<3)
        {
            name.setError("please enter Valid Name");
            valid=false;
        } if(email1.isEmpty()||email1.length()<3)
        {
            email.setError("please enter Valid Name");
            valid=false;
        }
        if(password1.isEmpty()||password1.length()<6)
        {
            password.setError("please enter Valid Username");
            valid=false;
        }
        if(repassword.isEmpty()||repassword.length()<6)
        {
            rePassword.setError("please enter Valid password");
            valid=false;
        }
        if(!(repassword.equals(password1)))
        {
            rePassword.setError("password and confirm password doesnot match");
            valid=false;
        }return  valid;
    }

}







