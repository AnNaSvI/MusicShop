package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String [] addresses;
    String subject="Order from Music Shop";
    String emailText;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Order");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent receiverOrderIntent =getIntent();
        String userName =receiverOrderIntent.getStringExtra("userName");
        String goodsName =receiverOrderIntent.getStringExtra("goodsName");
        int quantity =receiverOrderIntent.getIntExtra("quantity",0);
        double orderPrice =receiverOrderIntent.getDoubleExtra("price",0);
        emailText ="User Name: "+userName+"\n"+"Goods: "+goodsName+"\n"+"Quantity: " +quantity+"\n"+"Price: "+orderPrice;
        TextView orderTextView= findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
        TextView userEmail= findViewById(R.id.email);
        email=userEmail.getText().toString();
        String [] addresses={email};


    }

    public void submitOrder(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}