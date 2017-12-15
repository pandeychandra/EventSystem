package com.example.moonlight.eventsystemdemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrganizerAccount extends AppCompatActivity {
    EditText name, email, password, rePassword;
    String user,email1,password1,repassword;
    String type;
    FirebaseAuth firebaseAuth;
    String usernam,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_account);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        rePassword=(EditText)findViewById(R.id.cpassword);
        firebaseAuth = FirebaseAuth.getInstance();

    }
    public void userClick(View view) {

        user = name.getText().toString();
        email1 = email.getText().toString();
        password1 = password.getText().toString();
        repassword = rePassword.getText().toString();
        type=getIntent().getStringExtra("cost");

        register();
    }



       /* RegisterLoginWithMysql cc = new RegisterLoginWithMysql(this);
       cc.execute(method,user,email1,password1,repassword);
*/

    private void register() {
        final String method = "register";
        //final Firebase reference = new Firebase("https://advancedchatsystem-fb089.firebaseio.com/users");


        if (!validate()) {
            Toast.makeText(getApplicationContext(), "SignUp Failed", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(OrganizerAccount.this, "Please Wait", "Processing", true);

            (firebaseAuth.createUserWithEmailAndPassword(email1, password1))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), " not Sucessful", Toast.LENGTH_SHORT).show();

                            } else {


                                String tokn = FirebaseInstanceId.getInstance().getToken();


                                Toast.makeText(getApplicationContext(), "  Sucessful", Toast.LENGTH_SHORT).show();

                                RegisterLoginWithMysql cc = new RegisterLoginWithMysql(OrganizerAccount.this);
                                cc.execute(method, user, email1, password1, repassword, type, tokn);




                                progressDialog.dismiss();

                            }
                        }
                    });

}

    }










           /* RegisterLoginWithMysql cc = new RegisterLoginWithMysql(this);
            cc.execute(method,user,email1,password1,repassword,type);
*/



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









