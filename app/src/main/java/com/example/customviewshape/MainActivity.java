package com.example.customviewshape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customviewshape.custom_view.Shape_custom_view1;
import com.example.customviewshape.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Shape_custom_view1 cv_shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //
        cv_shape = (Shape_custom_view1) binding.mainCvShape1;

        binding.mainBtnSwapColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv_shape.SwapColor();
            }
        });
        //
        binding.mainBtnNextPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityPart2.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}