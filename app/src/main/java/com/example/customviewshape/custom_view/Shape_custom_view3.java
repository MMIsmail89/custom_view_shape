package com.example.customviewshape.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

import com.example.customviewshape.R;

import java.util.Timer;
import java.util.TimerTask;

public class Shape_custom_view3 extends View {
    //
    private static final int SQUARE_SIZE = 100;
    private Rect _RectSquare;
    private Paint _PaintSquare;
    private int _squareColor;
    private int _squareSize;
    //
    private Paint _PaintCircle;
    private float _circleRadius = 100f;
    private float _circleX, _circleY;
    //
    private Bitmap _bitmapMagdiPic;
    //
    public Shape_custom_view3(Context context) {
        super(context);
        init(null);
    }

    public Shape_custom_view3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Shape_custom_view3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Shape_custom_view3(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    //
    private void init(@Nullable AttributeSet set) {
        _PaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);

        _PaintCircle = new Paint();
        _PaintCircle.setAntiAlias(true);
        _PaintCircle.setColor(Color.parseColor("#00ccff"));

        _RectSquare = new Rect();

        if(set == null) {
            return;
        }
        //
        _bitmapMagdiPic = BitmapFactory.decodeResource(getResources(), R.drawable.magdi_app1);

        // >> to read  getWidth(), getHeight() correctly !!
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                // >> resize the image

                int padding = 50;
                _bitmapMagdiPic = getResizedBitmap(_bitmapMagdiPic,
                        getWidth() - padding, getHeight() - padding);
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        int _imageNewX = _bitmapMagdiPic.getWidth() - 50;
                        int _imageNewY = _bitmapMagdiPic.getHeight() - 50;

                        if(_imageNewX <=25 || _imageNewY<=25){

                            _imageNewX = getWidth()-50;
                            _imageNewY = getHeight()-50;
                            _bitmapMagdiPic = BitmapFactory.decodeResource(getResources(), R.drawable.magdi_app1);

                            _bitmapMagdiPic = getResizedBitmap(_bitmapMagdiPic,
                                    _imageNewX, _imageNewY);

                            postInvalidate();

                            cancel();
                            return;
                        }
                        _bitmapMagdiPic = getResizedBitmap(_bitmapMagdiPic,
                                _imageNewX, _imageNewY);
                        postInvalidate();

                    }
                }, 5001, 2001);
            }
        });


        // >> get the R.styleable.CustomView from the Context
        // TypedArray is often used to obtain attributes and values defined in XML resource files,
        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.Shape_custom_view2);

        // >> get the color of square from the designed Custom view of your context,
        // besides, make the default value is Green
        _squareColor = typedArray.getColor(R.styleable.Shape_custom_view2_square_color, Color.GREEN);
        _squareSize = typedArray.getDimensionPixelSize(R.styleable.Shape_custom_view2_square_size, SQUARE_SIZE);

        _PaintSquare.setColor(_squareColor);

        // >> After you have retrieved the values you need from the TypedArray,
        // like _squareColor,
        // you should call typedArray.recycle() to release the memory associated with the TypedArray object.
        typedArray.recycle();
    }

    private Bitmap getResizedBitmap(Bitmap bitmapInRequesting, int requiredWidth, int requiredHeight) {
        Matrix matrix = new Matrix();

        RectF source = new RectF(0,0,
                bitmapInRequesting.getWidth(), bitmapInRequesting.getHeight());
        RectF destination = new RectF(0,0,requiredWidth, requiredHeight);

        matrix.setRectToRect(source, destination, Matrix.ScaleToFit.CENTER);


        Bitmap resultingBitmap = Bitmap.createBitmap(bitmapInRequesting, 0, 0,
                bitmapInRequesting.getWidth(), bitmapInRequesting.getHeight(),
                matrix, true);



        return resultingBitmap;
    }

    //
    public void SwapColor(){
        _PaintSquare.setColor(
                _PaintSquare.getColor() == _squareColor ? Color.RED :_squareColor
        );

        postInvalidate();
    }
    //
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // >> draw Rectangle

        _RectSquare.left = 50;
        _RectSquare.top = 50;

        _RectSquare.right = _RectSquare.left + _squareSize;
        _RectSquare.bottom = _RectSquare.top + _squareSize;

        canvas.drawRect(_RectSquare, _PaintSquare);

        // >> draw Circle

        float cX, cY;
        float radius = 100f;

        cX = getWidth() - radius - 50f;
        cY = _RectSquare.top + ( _RectSquare.height() / 2 );

        cY = cY< radius ? radius : cY;


        if(_circleX == 0F && _circleY == 0F){
            _circleX = getWidth()/2;
            _circleY = getHeight()/2;
        }

        if(_circleX+_circleRadius > getWidth()){
            _circleX = getWidth()-_circleRadius;

        } else if (_circleX<_circleRadius) {
            _circleX = _circleRadius;
        }

        if(_circleY+_circleRadius > getHeight()){

            _circleY = getHeight()-_circleRadius;
        } else if (_circleY<_circleRadius) {
            _circleY = _circleRadius;
        }

        canvas.drawCircle(_circleX, _circleY, _circleRadius, _PaintCircle);
        //

        // >> Draw Bitmap
        float _imageX = (getWidth() - _bitmapMagdiPic.getWidth())/2;
        float _imageY = (getHeight() - _bitmapMagdiPic.getHeight())/2;

        canvas.drawBitmap(_bitmapMagdiPic, _imageX, _imageY, null);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // return super.onTouchEvent(event);
        /*
         >> value:
         typically indicating whether the touch event was consumed (handled) by the view or not.
         this boolean value might indicate whether the touch event was consumed or not.
         */
        boolean value = super.onTouchEvent(event);

        float x_touch = event.getX();
        float y_touch = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (_RectSquare.left < x_touch && _RectSquare.right > x_touch){
                    if(_RectSquare.top < y_touch && _RectSquare.bottom > y_touch){
                        // user touch within Rectangle body!!
                        _circleRadius += 15f;
                        postInvalidate();
                    }
                }

                return true;

            case MotionEvent.ACTION_MOVE:

                double dx_circle_p2 = Math.pow(x_touch - _circleX, 2);
                double dy_circle_p2 = Math.pow(y_touch - _circleY, 2);
                if(dx_circle_p2 + dy_circle_p2 < Math.pow(_circleRadius, 2)){
                    // User touch within the Circle body!!
                    _circleX = x_touch;
                    _circleY = y_touch;

                    postInvalidate();

                    return true;
                }
                return value;

            case MotionEvent.ACTION_UP:

                return true;

        }

        return value;
    }
    //

}
