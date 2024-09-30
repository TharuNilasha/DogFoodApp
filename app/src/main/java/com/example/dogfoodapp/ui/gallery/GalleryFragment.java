package com.example.dogfoodapp.ui.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogfoodapp.R;
import com.example.dogfoodapp.databinding.FragmentGalleryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private RecyclerView recyclerViewGallery;
    private GalleryAdapter galleryAdapter;
    private List<String> galleryImages;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Initialize RecyclerView and adapter
        recyclerViewGallery = view.findViewById(R.id.recyclerViewGallery);
        recyclerViewGallery.setLayoutManager(new LinearLayoutManager(getContext()));

        galleryImages = new ArrayList<>();
        galleryAdapter = new GalleryAdapter(galleryImages);
        recyclerViewGallery.setAdapter(galleryAdapter);

        // Fetch gallery images from Firebase
        loadGalleryImages();

        return view;
    }

    private void loadGalleryImages() {
        DatabaseReference galleryRef = FirebaseDatabase.getInstance().getReference("gallery");

        galleryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                galleryImages.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String imageUrl = dataSnapshot.child("imageLink").getValue(String.class);
                    if (imageUrl != null) {
                        galleryImages.add(imageUrl);
                    }
                }
                galleryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    // RecyclerView Adapter for displaying gallery images
    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
        private final List<String> imageUrls;

        public GalleryAdapter(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        @NonNull
        @Override
        public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery_image, parent, false);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
            String imageUrl = imageUrls.get(position);

            // Load the image using AsyncTask
            new LoadImageTask(holder.galleryImageView).execute(imageUrl);
        }

        @Override
        public int getItemCount() {
            return imageUrls.size();
        }

        public class GalleryViewHolder extends RecyclerView.ViewHolder {
            ImageView galleryImageView;

            public GalleryViewHolder(@NonNull View itemView) {
                super(itemView);
                galleryImageView = itemView.findViewById(R.id.galleryImageView);
            }
        }
    }

    // AsyncTask for downloading the image in the background
    private static class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        private final ImageView imageView;

        public LoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
            }
        }
    }
}