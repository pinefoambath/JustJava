
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */

    public void increment(View view) {

        if (quantity == 10) {
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */

    public void decrement(View view) {

        if (quantity == 1) {
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        EditText nameField = findViewById(R.id.name_field);
        String nameCustomer = nameField.getText().toString();
        boolean whippedCream = ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked();
        boolean chocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        int price = calculatePrice(whippedCream, chocolate);
        String confirmationOrder = "Name: " + nameCustomer + "\nAdd Whipped Cream? " + whippedCream + "\nAdd Chocolate? " + chocolate + "\nQuantity: " + quantity + "\nTotal $" + price + "\nThank you!";
        composeEmail("pants@gmail.com", "order for", confirmationOrder);


    }

    public void composeEmail(String addresses, String subject, String confirmationOrder) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, confirmationOrder);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    /**
     * Calculates the price of the order.
     * <p>
     * is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int condimentPrice = 5;
        if (whippedCream) {
            condimentPrice = condimentPrice + 1;
        }
        if (chocolate) {
            condimentPrice = condimentPrice + 2;
        }
        // int price = quantity * condimentPrice;
        // return price;}
        return quantity * condimentPrice;
    }


}



