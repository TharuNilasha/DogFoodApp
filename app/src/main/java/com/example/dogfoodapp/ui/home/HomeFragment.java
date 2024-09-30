package com.example.dogfoodapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogfoodapp.Articles.Article1;
import com.example.dogfoodapp.Articles.Article2;
import com.example.dogfoodapp.Articles.Article3;
import com.example.dogfoodapp.Articles.Article4;
import com.example.dogfoodapp.Articles.Article5;
import com.example.dogfoodapp.Articles.Article6;
import com.example.dogfoodapp.R;
import com.example.dogfoodapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout using view binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot(); // Get the root view from the binding

        // Set up click listener for the first card's button
        binding.continueButtonCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Article1.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the first card's button
        binding.continueButtonCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Article2.class);
                startActivity(intent);
            }
        });


        // Set up click listener for the first card's button
        binding.continueButtonCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Article3.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the first card's button
        binding.continueButtonCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Article4.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the first card's button
        binding.continueButtonCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Article5.class);
                startActivity(intent);
            }
        });

        // Set up click listener for the first card's button
        binding.continueButtonCard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Article6.class);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}