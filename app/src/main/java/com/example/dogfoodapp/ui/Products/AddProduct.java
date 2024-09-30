package com.example.dogfoodapp.ui.Products;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dogfoodapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {


    private EditText idEditText, nameEditText, costEditText, priceEditText, quantityEditText, imageLinkEditText;
    private Button saveButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        // Initialize UI elements
        idEditText = findViewById(R.id.idEditText);
        nameEditText = findViewById(R.id.nameEditText);
        costEditText = findViewById(R.id.costEditText);
        priceEditText = findViewById(R.id.priceEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        imageLinkEditText = findViewById(R.id.imageLinkEditText);
        saveButton = findViewById(R.id.saveButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });
    }

    private void saveProduct() {
        // Get input values
        String id = idEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String cost = costEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String quantity = quantityEditText.getText().toString().trim();
        String imageLink = imageLinkEditText.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(name) || TextUtils.isEmpty(cost) ||
                TextUtils.isEmpty(price) || TextUtils.isEmpty(quantity) || TextUtils.isEmpty(imageLink)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check for duplicate ID
        databaseReference.child(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    // ID already exists
                    Toast.makeText(this, "Product ID already exists", Toast.LENGTH_SHORT).show();
                } else {
                    // ID is unique, save data
                    Map<String, Object> productData = new HashMap<>();
                    productData.put("id", id);
                    productData.put("name", name);
                    productData.put("cost", cost);
                    productData.put("price", price);
                    productData.put("quantity", quantity);
                    productData.put("imageLink", imageLink);

                    databaseReference.child(id).setValue(productData).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
                            clearFields();
                        } else {
                            Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Toast.makeText(this, "Error checking ID", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearFields() {
        idEditText.setText("");
        nameEditText.setText("");
        costEditText.setText("");
        priceEditText.setText("");
        quantityEditText.setText("");
        imageLinkEditText.setText("");
    }
}