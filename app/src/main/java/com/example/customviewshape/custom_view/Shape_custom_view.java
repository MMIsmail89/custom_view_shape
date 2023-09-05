package com.example.customviewshape.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Shape_custom_view extends View {
    private static final int SQUARE_SIZE = 250;
    private Rect _RectSquare;
    private Paint _PaintSquare;
    public Shape_custom_view(Context context) {
        super(context);
        init(null);
    }

    public Shape_custom_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Shape_custom_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Shape_custom_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    //
    private void init(@Nullable AttributeSet set) {
        _PaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);
        _RectSquare = new Rect();
        _PaintSquare.setColor(Color.GREEN);

    }
    //

    public void SwapColor(){
        _PaintSquare.setColor(
                _PaintSquare.getColor() == Color.GREEN ? Color.RED :Color.GREEN
        );

        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        _RectSquare.left = 50;
        _RectSquare.top = 50;

        _RectSquare.right = _RectSquare.left + SQUARE_SIZE;
        _RectSquare.bottom = _RectSquare.top + SQUARE_SIZE;

        canvas.drawRect(_RectSquare, _PaintSquare);

    }
}
