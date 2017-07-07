package com.wang.graybitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ConorImageView conorImageView,connerImageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conorImageView = (ConorImageView) findViewById(R.id.connerImageView);
        connerImageView2 = (ConorImageView) findViewById(R.id.connerImageView2);
        initBitmap();
    }

    private void initBitmap() {
        String imagePath ="http://www.zjito.com/upload/resources/image/2015/11/21/0d362aed-edad-42a2-9fcf-d7e67fcc2429_720x1500.jpg?1483574081000";
        Glide.with(this).load(imagePath).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ByteArrayOutputStream bys = new ByteArrayOutputStream(1024*60);
                resource.compress(Bitmap.CompressFormat.JPEG,100,bys);
                byte[] byteArray = bys.toByteArray();
                Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromByte(byteArray, conorImageView.getMeasuredWidth(), conorImageView.getMeasuredHeight());
                if(bitmap != null) {
                    conorImageView.setBitmap(bitmap);
                }
                Log.e("CHAO", "initBitmap111"+resource.getWidth()+"===="+resource.getHeight());
                Log.e("CHAO", "conorImageView222"+conorImageView.getMeasuredWidth()+"===="+ conorImageView.getMeasuredHeight());
            }
        });
        Glide.with(this).load(imagePath).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ByteArrayOutputStream bys = new ByteArrayOutputStream(1024*60);
                resource.compress(Bitmap.CompressFormat.JPEG,100,bys);
                byte[] byteArray = bys.toByteArray();
                Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromByte(byteArray, conorImageView.getMeasuredWidth(), conorImageView.getMeasuredHeight());
                if(bitmap != null) {
                    Bitmap grey = grey(bitmap);
                    connerImageView2.setBitmap(grey);
                }
                Log.e("CHAO", "initBitmap111"+resource.getWidth()+"===="+resource.getHeight());
                Log.e("CHAO", "conorImageView222"+conorImageView.getMeasuredWidth()+"===="+ conorImageView.getMeasuredHeight());
            }
        });
    }

    public static final Bitmap grey(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }
}
