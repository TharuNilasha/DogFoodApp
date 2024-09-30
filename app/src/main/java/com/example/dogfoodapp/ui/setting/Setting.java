package com.example.dogfoodapp.ui.setting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dogfoodapp.MainActivity;
import com.example.dogfoodapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Setting extends Fragment {


    private EditText idEditText, nameEditText, ageEditText, addressEditText, mobileEditText, usernameEditText, passwordEditText;
    private Button updatePasswordButton;
    private DatabaseReference databaseUsers;
    private String username;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Initialize views
        idEditText = view.findViewById(R.id.idTextView);
        nameEditText = view.findViewById(R.id.nameTextView);
        ageEditText = view.findViewById(R.id.ageTextView);
        addressEditText = view.findViewById(R.id.addressTextView);
        mobileEditText = view.findViewById(R.id.mobileTextView);
        usernameEditText = view.findViewById(R.id.usernameTextView);
        passwordEditText = view.findViewById(R.id.passwordTextView);
        updatePasswordButton = view.findViewById(R.id.updatePasswordButton);

        // Get the username from the MainActivity
        if (getActivity() instanceof MainActivity) {
            username = ((MainActivity) getActivity()).getUsername();
        }

        if (username == null || username.isEmpty()) {
            Toast.makeText(getContext(), "No username provided", Toast.LENGTH_SHORT).show();
            return view;
        }

        // Initialize Firebase reference
        databaseUsers = FirebaseDatabase.getInstance().getReference("user");

        // Load user data from Firebase
        loadUserData();

        // Handle update button click
        updatePasswordButton.setOnClickListener(v -> updateUserData());

        return view;
    }

    private void loadUserData() {
        if (username != null) {
            databaseUsers.child(username).get().addOnSuccessListener(snapshot -> {
                if (snapshot.exists()) {
                    // Get user details and display them
                    String id = snapshot.child("id").getValue(String.class);
                    String name = snapshot.child("name").getValue(String.class);
                    String age = snapshot.child("age").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);
                    String mobile = snapshot.child("mobile").getValue(String.class);
                    String password = snapshot.child("password").getValue(String.class);

                    idEditText.setText(id);
                    nameEditText.setText(name);
                    ageEditText.setText(age);
                    addressEditText.setText(address);
                    mobileEditText.setText(mobile);
                    usernameEditText.setText(username); // Show username from MainActivity
                    passwordEditText.setText(password);
                } else {
                    Toast.makeText(getContext(), "User data not found", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> {
                Toast.makeText(getContext(), "Failed to load data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void updateUserData() {
        String updatedId = idEditText.getText().toString();
        String updatedName = nameEditText.getText().toString();
        String updatedAge = ageEditText.getText().toString();
        String updatedAddress = addressEditText.getText().toString();
        String updatedMobile = mobileEditText.getText().toString();
        String updatedPassword = passwordEditText.getText().toString();

        if (updatedId.isEmpty() || updatedName.isEmpty() || updatedAge.isEmpty() ||
                updatedAddress.isEmpty() || updatedMobile.isEmpty() || updatedPassword.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a user object with the updated values
        User updatedUser = new User(updatedId, updatedName, updatedAge, updatedAddress, updatedMobile, updatedPassword);

        // Update the data in Firebase
        databaseUsers.child(username).setValue(updatedUser)
                .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "User data updated successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to update user data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    // User class to hold user data
    public static class User {
        public String id, name, age, address, mobile, password;

        public User() {
        }

        public User(String id, String name, String age, String address, String mobile, String password) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
            this.mobile = mobile;
            this.password = password;
        }
    }
}