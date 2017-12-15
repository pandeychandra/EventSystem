package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

import static com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION;

public class Booking extends AppCompatActivity {
    TextView textView;
    TextView cost1,cost2,cost3;
    Button button;

    PayPalConfiguration m_configuration;
    String m_paypalClientId = "AV6wPF52f_h7r5R7pmxJFpJwamEVuL0yIuD3poNrwHe8Q3Po_geuLvK7xd5oNZ_tXkSBhVPLeMy1gTxZ";
    Intent m_service;
    int m_paypalRequestCode =999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        textView = (TextView)findViewById(R.id.text);
        button = (Button)findViewById(R.id.button);


        m_configuration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)   //sandbox for test, production for real
                .clientId(m_paypalClientId);

        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);   //configuration above
        startService(m_service);   //paypal service, listining to calls to paypal app


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });
    }

    void pay(){
        PayPalPayment payment = new PayPalPayment(new BigDecimal("10"), "USD", "Test payment with PayPal",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, m_paypalRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == m_paypalRequestCode){
            if(resultCode == RESULT_OK){
                //we have to confirm that the payment worked to avoid fraud
                PaymentConfirmation confirmation = data.getParcelableExtra(EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null){
                    String state = confirmation.getProofOfPayment().getState();

                    if(state.equals("approved"))  //if the payment worked, the state equals approved
                        textView.setText("Payment approved");
                    else
                        textView.setText("Error in payment");
                }else {
                    textView.setText("Confirmation is null");
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
