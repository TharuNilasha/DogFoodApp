package com.example.dogfoodapp.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dogfoodapp.R;
import com.example.dogfoodapp.ui.Products.AddProduct;
import com.example.dogfoodapp.ui.gallery.AddGallery;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.addGalleryCard).setOnClickListener(v -> {
            // Redirect to AddGallery activity
            Intent intent = new Intent(AdminHome.this, AddGallery.class);
            startActivity(intent);
        });

        findViewById(R.id.addProductsCard).setOnClickListener(v -> {
            // Redirect to AddProduct activity
            Intent intent = new Intent(AdminHome.this, AddProduct.class);
            startActivity(intent);
        });
    }
}