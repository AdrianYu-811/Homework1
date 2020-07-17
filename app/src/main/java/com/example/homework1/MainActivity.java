package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;

import android.graphics.Bitmap;

import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//This is Adrian's project

public class MainActivity extends AppCompatActivity {

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!OpenCVLoader.initDebug())
        {
            Log.d("check","No OpenCv");
        }
        else{
            Log.d("check","OpenCv OK");
        }
        Button btn = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        final int[] count = {0};

        try {
            btn.setOnClickListener(new View.OnClickListener() {
                Mat img = Utils.loadResource(MainActivity.this, R.drawable.apple, CvType.CV_8UC4);

                @Override
                public void onClick(View v) {
                    try {
                        count[0]++;
                        img = onClcik(img, count[0], img.width()/4, img.height()/4);
                        Bitmap bitmap=Bitmap.createBitmap(img.width(),img.height(), Bitmap.Config.ARGB_8888);
                        ImageView imageView = findViewById(R.id.imageView3);
                        Utils.matToBitmap(img,bitmap);
                        imageView.setImageBitmap(bitmap);

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Mat onClcik(Mat img, int count, int width, int height){
        Point firstPoint = new Point();
        Point secondPoint = new Point();
        Scalar linecolor = new Scalar(255,0,0,255);
        int linnewidth = 3;

        switch (count){
            case 1:
                firstPoint.x=width;
                firstPoint.y=height;
                secondPoint.x=width;
                secondPoint.y=img.height()-height;
                Imgproc.line(img,firstPoint,secondPoint,linecolor,linnewidth);
                return img;
            case 2:
                firstPoint.x=width;
                firstPoint.y=img.height()-height;
                secondPoint.x=img.width()-width;
                secondPoint.y=img.height()-height;
                Imgproc.line(img,firstPoint,secondPoint,linecolor,linnewidth);
                return img;
            case 3:
                firstPoint.x=img.width()-width;
                firstPoint.y=height;
                secondPoint.x=img.width()-width;
                secondPoint.y=img.height()-height;
                Imgproc.line(img,firstPoint,secondPoint,linecolor,linnewidth);
                return img;
            case 4:
                firstPoint.x=img.width()-width;
                firstPoint.y=height;
                secondPoint.x=width;
                secondPoint.y=height;
                Imgproc.line(img,firstPoint,secondPoint,linecolor,linnewidth);
                return img;
            default:
                return img;

        }
    }
}


