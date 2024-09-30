package com.example.dogfoodapp.MainPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dogfoodapp.R;
import com.example.dogfoodapp.Register.LoginActivity;
import com.example.dogfoodapp.Register.RegisterActivity;

public class MainPage extends AppCompatActivity {

    private Button createAccountButton;
    private Button signInButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);

        //adjust the system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        createAccountButton = findViewById(R.id.createAccountButton);
        signInButton = findViewById(R.id.signInButton);

        // Set up button click listeners
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RegisterUser activity
                Intent intent = new Intent(MainPage.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginUser activity
                Intent intent = new Intent(MainPage.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Set hover listener to change cursor to hand type
        createAccountButton.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_HOVER_ENTER) {
                    // Change the pointer to hand cursor
                    v.setPointerIcon(PointerIcon.getSystemIcon(MainPage.this, PointerIcon.TYPE_HAND));
                }
                return false; // Let the event continue to be handled by the default implementation
            }
        });
    }
}