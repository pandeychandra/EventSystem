package com.example.moonlight.eventsystemdemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

public class UserAccount extends AppCompatActivity {
    EditText name, email, password, rePassword;
    String user,email1,password1,repassword;
    String type;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
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





    private void register() {
        final String method = "reg";


        if(!validate())
        {
            Toast.makeText(getApplicationContext(),"SignUp Failed",Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog progressDialog = ProgressDialog.show(UserAccount.this, "Please Wait", "Processing", true);

            (firebaseAuth.createUserWithEmailAndPassword(email1, password1))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), " not Sucessful", Toast.LENGTH_SHORT).show();

                            } else {

                                FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = mUser.getUid();
                                Toast.makeText(getApplicationContext(), "id is" + uid, Toast.LENGTH_LONG);
                                String uname = mUser.getEmail();
                                String tokn = FirebaseInstanceId.getInstance().getToken();
                                //String reg_id = CustomSharedPreManager.getInstance(OwnerAccount.this).getToken();
                                Toast.makeText(getApplicationContext(), "  Sucessful", Toast.LENGTH_SHORT).show();

                                RegisterLoginWithMysql cc = new RegisterLoginWithMysql(UserAccount.this);
                                cc.execute(method, user, email1, password1, repassword, type);

                                progressDialog.dismiss();
                            }
                        }
                    });


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









