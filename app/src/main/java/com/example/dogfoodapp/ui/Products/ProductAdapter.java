package com.example.dogfoodapp.ui.Products;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogfoodapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firestore.v1.StructuredQuery;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private DatabaseReference databaseReference;
    private Context context;
    private String username; // Assume you pass the username to the adapter

    public ProductAdapter(List<Product> productList, Context context, String username) {
        this.productList = productList;
        this.context = context;
        this.username = username;
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productId.setText(product.getId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText("Price: " + product.getPrice());
        holder.productQuantity.setText("Quantity: " + product.getQuantity());

        // Load image using AsyncTask
        new ImageLoadTask(holder.productImageView).execute(product.getImageLink());

        holder.orderButton.setOnClickListener(v -> showOrderDialog(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private void showOrderDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Order Quantity");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String quantityStr = input.getText().toString();
            if (!quantityStr.isEmpty()) {
                int quantity = Integer.parseInt(quantityStr);
                processOrder(product, quantity);
            } else {
                Toast.makeText(context, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void processOrder(Product product, int quantity) {
        if (quantity <= 0) {
            Toast.makeText(context, "Invalid quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        int availableQuantity = Integer.parseInt(product.getQuantity());
        if (quantity > availableQuantity) {
            Toast.makeText(context, "Not enough stock", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save order
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("orders");
        String orderId = ordersRef.push().getKey();
        // Assuming product price is available as a double in the Product class
        double productPrice = Double.parseDouble(product.getPrice());
        double totalPrice = quantity * productPrice;
        Order order = new Order(orderId, product.getId(), product.getName(), quantity, username, totalPrice, product.getImageLink());
        ordersRef.child(orderId).setValue(order).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Decrease product quantity
                int newQuantity = availableQuantity - quantity;
                databaseReference.child(product.getId()).child("quantity").setValue(String.valueOf(newQuantity))
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(context, "Order placed successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Failed to update quantity", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(context, "Failed to place order", Toast.LENGTH_SHORT).show();
            }
        });
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productId, productName, productPrice, productQuantity;
        Button orderButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productId = itemView.findViewById(R.id.productId);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            orderButton = itemView.findViewById(R.id.orderButton);
        }
    }

    // AsyncTask class for loading images
    private static class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView imageView;

        public ImageLoadTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlString = strings[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                // Set a placeholder or error image if needed
            }
        }
    }
}
