package com.example.dogfoodapp.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogfoodapp.MainActivity;
import com.example.dogfoodapp.R;
import com.example.dogfoodapp.ui.Products.Order;
import com.example.dogfoodapp.ui.Products.OrderAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;
    private DatabaseReference ordersRef;
    private String username;
    private TextView totalPriceTextView;
    private TextView itemCountTextView;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // Get the username from the MainActivity
        if (getActivity() instanceof MainActivity) {
            username = ((MainActivity) getActivity()).getUsername();
        }

        if (username == null || username.isEmpty()) {
            Toast.makeText(getContext(), "No username provided", Toast.LENGTH_SHORT).show();
            return view;
        }

        // Initialize Firebase Database reference
        ordersRef = FirebaseDatabase.getInstance().getReference("orders");

        // Initialize RecyclerView and adapter
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);

        // Initialize summary TextViews
        totalPriceTextView = view.findViewById(R.id.totalPriceTextView);
        itemCountTextView = view.findViewById(R.id.itemCountTextView);

        // Fetch and display orders
        fetchOrders();

        return view;
    }

    private void fetchOrders() {
        ordersRef.orderByChild("username").equalTo(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderList.clear();
                double totalPrice = 0;
                int itemCount = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Order order = dataSnapshot.getValue(Order.class);
                    if (order != null) {
                        orderList.add(order);
                        totalPrice += order.getPrice() * order.getQuantity();
                        itemCount += order.getQuantity();
                    }
                }

                orderAdapter.notifyDataSetChanged();
                updateSummary(totalPrice, itemCount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors
            }
        });
    }

    private void updateSummary(double totalPrice, int itemCount) {
        totalPriceTextView.setText("Total Price: $" + String.format("%.2f", totalPrice));
        itemCountTextView.setText("Item Count: " + itemCount);
    }
}