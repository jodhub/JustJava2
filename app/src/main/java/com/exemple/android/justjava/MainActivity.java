package com.exemple.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private int quantity = 1;




    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: " + name;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nTotal: " + calculatePrice(addWhippedCream, addChocolate ) + "$";
        priceMessage = priceMessage + "\nThank you!";


        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        EditText name = (EditText) findViewById(R.id.name);
        String theName = name.getText().toString();
        boolean hasWippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWippedCream, hasChocolate);
        String priceMessage =createOrderSummary(theName, price, hasWippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + theName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /**
     * Calculates the price of the order.
     *"return total price
     *
     */
    //This is a test, I change some lines
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice =  5;
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolate){
            basePrice = basePrice +2;
        }
        return basePrice * quantity;

    }

    public void increment(View view) {
        if (quantity > 9) {
            Context context = getApplicationContext();
            CharSequence text = "More than 10 coffees will kill you bro";
            int duration = Toast.LENGTH_SHORT;

            Toast tooMuch = Toast.makeText(context, text, duration);
            tooMuch.show();}
        else
        {quantity++;
            displayQuantity(quantity);}
    }


    public void decrement(View view) {
        if (quantity < 1) {
            Context context = getApplicationContext();
            CharSequence text = "Fucking grateux";
            int duration = Toast.LENGTH_SHORT;

            Toast notMuch = Toast.makeText(context, text, duration);
            notMuch.show();}
        else {
            quantity--;
            displayQuantity(quantity);}



    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */

}