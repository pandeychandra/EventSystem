package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import static com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION;

public class Booking1 extends AppCompatActivity {
    TextView response;

    PayPalConfiguration m_configuration;
    String m_paypalClientId = "AV6wPF52f_h7r5R7pmxJFpJwamEVuL0yIuD3poNrwHe8Q3Po_geuLvK7xd5oNZ_tXkSBhVPLeMy1gTxZ";
    Intent m_service;
    int m_paypalRequestCode =999;
    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;



    PayPalPayment thingToBuy;
    String n,p;
    String c1,c2,c3;
    String s,ename,token;
    Button button;
    EditText noOfTicket;
    int totalcost=0,f,pi;
    String b;
    AlertDialog dialog;
    TextView cost1,cost2,cost3;
    String price1,price2,price3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking1);
        response = (TextView) findViewById(R.id.text);
        cost1=(TextView)findViewById(R.id.cat1prize) ;
       /* cost2=(TextView)findViewById(R.id.cat2prize);
        cost3=(TextView)findViewById(R.id.cat3prize);*/

        SharedPreferences sharedPreferences = getSharedPreferences("Tool", MODE_PRIVATE);
        n = sharedPreferences.getString("name", "");
        p = sharedPreferences.getString("password", "");
       price1 = getIntent().getStringExtra("costs1");
       /* price2 = getIntent().getStringExtra("costs2");
        price3 = getIntent().getStringExtra("costs3");*/
        noOfTicket = (EditText) findViewById(R.id.ticketno);
        button = (Button) findViewById(R.id.boking);
       /* c1 = getIntent().getStringExtra("cost1");
        c2 = getIntent().getStringExtra("cost2");
        c3 = getIntent().getStringExtra("cost3");

        ename = getIntent().getStringExtra("eName");
        token = getIntent().getStringExtra("t");

        cost1.setText("$"+c1);
        cost2.setText("$"+c2);
        cost3.setText("$"+c3);*/
        m_configuration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)   //sandbox for test, production for real
                .clientId(m_paypalClientId);

        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);   //configuration above
        startService(m_service);

        //Button mShowDialog = (Button) findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b = noOfTicket.getText().toString();
                totalcost = Integer.parseInt(b);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Booking1.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_booking, null);
                final TextView t1 = (TextView) mView.findViewById(R.id.total);
                final TextView t2 = (TextView) mView.findViewById(R.id.discount);
                final TextView t3 = (TextView) mView.findViewById(R.id.subtotal);
                Button mLogin = (Button) mView.findViewById(R.id.fbutton);
                mBuilder.setView(mView);
                dialog = mBuilder.create();
                pi=Integer.parseInt(price1);
               f = pi * totalcost;

                t1.setText(String.valueOf("$" + f + ".00"));
                t2.setText("-$ 0.00");
                t3.setText(String.valueOf("$" + f + ".00"));
                dialog.show();
                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        thingToBuy = new PayPalPayment(new java.math.BigDecimal(f), "USD", "Test Payment With Paypal",
                                PayPalPayment.PAYMENT_INTENT_SALE);
                        Intent intent = new Intent(Booking1.this, PaymentActivity.class);

                        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
                        startActivityForResult(intent, REQUEST_CODE_PAYMENT);


                    }

                });

            }
        });



}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    String method="pay";
                    String msg=n+"is booking";




                   //String tkn = FirebaseInstanceId.getInstance().getToken();
                        RegisterLoginWithMysql cc = new RegisterLoginWithMysql(this);
                    cc.execute(method,p,ename);
                   RegisterLoginWithMysql c = new RegisterLoginWithMysql(this);
                    c.execute("nof",token,msg);
                    response.setText("payment approved");
                    dialog.dismiss();





                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println("The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                System.out
                        .println("An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        } else if (requestCode == REQUEST_CODE_FUTURE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PayPalAuthorization auth = data
                        .getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if (auth != null) {
                    try {
                        Log.i("FuturePaymentExample", auth.toJSONObject()
                                .toString(4));

                        String authorization_code = auth.getAuthorizationCode();
                        Log.i("FuturePaymentExample", authorization_code);

                        sendAuthorizationToServer(auth);
                        Toast.makeText(getApplicationContext(),
                                "Future Payment code received from PayPal",
                                Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        Log.e("FuturePaymentExample",
                                "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("FuturePaymentExample", "The user canceled.");
            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("FuturePaymentExample",
                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
            }
        }
    }

    private void sendAuthorizationToServer(PayPalAuthorization authorization) {

    }

    public void onFuturePaymentPurchasePressed(View pressed) {
        // Get the Application Correlation ID from the SDK
        String correlationId = PayPalConfiguration
                .getApplicationCorrelationId(this);

        Log.i("FuturePaymentExample", "Application Correlation ID: "
                + correlationId);

        // TODO: Send correlationId and transaction details to your server for
        // processing with
        // PayPal...
        Toast.makeText(getApplicationContext(),
                "App Correlation ID received from SDK", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onDestroy() {
        // Stop service when done
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}


