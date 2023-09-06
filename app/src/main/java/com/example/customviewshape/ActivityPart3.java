package com.example.customviewshape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customviewshape.custom_view.Shape_custom_view3;

import com.example.customviewshape.databinding.ActivityPart3Binding;

public class ActivityPart3 extends AppCompatActivity {

    private ActivityPart3Binding binding;
    private Shape_custom_view3 cv_shape;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_part3);
        binding = ActivityPart3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //
        cv_shape = (Shape_custom_view3) binding.part3CvShape1;


        //
        binding.part3BtnNextPart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.part3BtnReturnPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPart3.this, ActivityPart2.class);
                startActivity(intent);
            }
        });
    }
}