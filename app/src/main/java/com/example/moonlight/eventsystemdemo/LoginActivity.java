package com.example.moonlight.eventsystemdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    RadioButton user,organizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.fullnameField);
        password=(EditText)findViewById(R.id.passwordField);
        /*user=(RadioButton)findViewById(R.id.user);
        organizer=(RadioButton)findViewById(R.id.org);*/
    }
    public void signup(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Account");
        builder.setMessage("Which account you want to create");
        builder.setPositiveButton("User Acount", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myintent=new Intent(LoginActivity.this, UserAccount.class);
                myintent.putExtra("cost","User");
                startActivity(myintent);


            }
        });
        builder.setNegativeButton("Organizer Account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myintent=new Intent(LoginActivity.this, OrganizerAccount.class);
                myintent.putExtra("cost","Organizer");
                startActivity(myintent);



            }


        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();





    }
    public void login(View view)
    {
        String user=username.getText().toString();
        String pass=password.getText().toString();
        String method="login";

       /* BackgroundTask backgroundTask=new BackgroundTask(User_Login.this);
        backgroundTask.execute(method,user,pass);*/
       RegisterLoginWithMysql registerLoginWithMysql=new RegisterLoginWithMysql(this);
        registerLoginWithMysql.execute(method,user,pass);

    }

}
