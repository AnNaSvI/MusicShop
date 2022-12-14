package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity=0;
    Spinner spinner;
    ArrayList  spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createMap();
        userNameEditText = findViewById(R.id.nameEditText);


    }
    void createMap () {
        goodsMap = new HashMap();
        goodsMap.put("guitar",500.0);
        goodsMap.put("drums",1000.0);
        goodsMap.put("keyboard",800.0);
    }
    void createSpinner () {
        spinner= findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList= new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void increaseQuantity(View view) {
        quantity = quantity+1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(""+quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity*price);

    }

    public void decreasQuantity(View view) {
        quantity = quantity - 1;
        if (quantity<0) {
            quantity = 0;
        }
            TextView quantityTextView = findViewById(R.id.quantityTextView);
            quantityTextView.setText(""+quantity);
            TextView priceTextView = findViewById(R.id.priceTextView);
            priceTextView.setText(""+quantity*price);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName=spinner.getSelectedItem().toString();
        price=(double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity*price);

        ImageView goodsImageView;
        goodsImageView=findViewById(R.id.goodsImageView);
       switch (goodsName){
           case "guitar":
               goodsImageView.setImageResource(R.drawable.guitar);
               break;
           case "drums":
               goodsImageView.setImageResource(R.drawable.drums);
               break;
           case "keyboard":
               goodsImageView.setImageResource(R.drawable.keyboard);
               break;
           default:
               goodsImageView.setImageResource(R.drawable.guitar);
               break;
       }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addtocart(View view) {
        Order order = new Order();
        order.userName= userNameEditText.getText().toString();
        order.goodsName=goodsName;
        order.quantity= quantity;
        order.orderPrice=quantity*price;
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName",order.userName);
        orderIntent.putExtra("goodsName",order.goodsName);
        orderIntent.putExtra("quantity",order.quantity);
        orderIntent.putExtra("price",order.orderPrice);
        startActivity(orderIntent);

    }




}