package com.example.dogfoodapp.Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dogfoodapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText idEditText, nameEditText, ageEditText, addressEditText, mobileEditText, usernameEditText, passwordEditText;
    private Button registerButton;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase reference to "user" table
        databaseUsers = FirebaseDatabase.getInstance().getReference("user");

        // Initialize UI components
        idEditText = findViewById(R.id.idEditText);
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        addressEditText = findViewById(R.id.addressEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        // Register button onClick listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Get the input values
        String id = idEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(name) || TextUtils.isEmpty(age) ||
                TextUtils.isEmpty(address) || TextUtils.isEmpty(mobile) ||
                TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check password length
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check for duplicate username
        databaseUsers.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Username already exists
                    Toast.makeText(RegisterActivity.this, "Username already exists. Please choose another one.", Toast.LENGTH_SHORT).show();
                } else {
                    // Register the user
                    User user = new User(id, name, age, address, mobile, username, password);
                    databaseUsers.child(username).setValue(user).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            clearFields();  // Clear input fields after successful registration
                            goToLogin();    // Redirect to login page
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegisterActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to clear the input fields
    private void clearFields() {
        idEditText.setText("");
        nameEditText.setText("");
        ageEditText.setText("");
        addressEditText.setText("");
        mobileEditText.setText("");
        usernameEditText.setText("");
        passwordEditText.setText("");
    }

    // Method to go to the login page after successful registration
    private void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();  // Optional: Close the RegisterActivity
    }

    // User class representing the user data to be stored in Firebase
    public static class User {
        public String id, name, age, address, mobile, username, password;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String id, String name, String age, String address, String mobile, String username, String password) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
            this.mobile = mobile;
            this.username = username;
            this.password = password;
        }
    }
}