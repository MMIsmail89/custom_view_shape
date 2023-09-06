package com.example.customviewshape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customviewshape.custom_view.Shape_custom_view2;
import com.example.customviewshape.databinding.ActivityPart2Binding;

public class ActivityPart2 extends AppCompatActivity {

    private ActivityPart2Binding binding;
    private Shape_custom_view2 cv_shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_part2);
        binding = ActivityPart2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //
        cv_shape = (Shape_custom_view2) binding.part2CvShape2;

        binding.part2BtnSwapColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv_shape.SwapColor();
            }
        });


        //
        //
        binding.part2BtnNextPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPart2.this, ActivityPart3.class);
                startActivity(intent);
            }
        });

        binding.part2BtnReturnPart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPart2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}