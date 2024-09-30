package com.example.dogfoodapp.ui.gallery;

import android.annotation.SuppressLint;
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

public class AddGallery extends AppCompatActivity {

    private EditText idEditText, imageLinkEditText;
    private Button addGalleryButton;
    private DatabaseReference databaseGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gallery);

        // Initialize Firebase reference with custom URL
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dogfoodappnew-default-rtdb.firebaseio.com/");
        databaseGallery = database.getReference("gallery");

        // Initialize UI components
        idEditText = findViewById(R.id.idEditText);
        imageLinkEditText = findViewById(R.id.imageLinkEditText);
        addGalleryButton = findViewById(R.id.addGalleryButton);

        // Add button onClick listener
        addGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGalleryItem();
            }
        });
    }

    private void addGalleryItem() {
        // Get the input values
        String id = idEditText.getText().toString().trim();
        String imageLink = imageLinkEditText.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(imageLink)) {
            Toast.makeText(this, "Please fill in both ID and image link", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the ID already exists in Firebase
        databaseGallery.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // ID already exists
                    Toast.makeText(AddGallery.this, "ID already exists. Please choose another one.", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the new gallery item to Firebase
                    GalleryItem galleryItem = new GalleryItem(id, imageLink);
                    databaseGallery.child(id).setValue(galleryItem).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddGallery.this, "Gallery item added successfully", Toast.LENGTH_SHORT).show();
                            clearFields();  // Clear the input fields after saving
                        } else {
                            Toast.makeText(AddGallery.this, "Failed to add gallery item", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddGallery.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to clear the input fields
    private void clearFields() {
        idEditText.setText("");
        imageLinkEditText.setText("");
    }

    // GalleryItem class representing the data to be stored in Firebase
    public static class GalleryItem {
        public String id;
        public String imageLink;

        public GalleryItem() {
            // Default constructor required for calls to DataSnapshot.getValue(GalleryItem.class)
        }

        public GalleryItem(String id, String imageLink) {
            this.id = id;
            this.imageLink = imageLink;
        }
    }
}