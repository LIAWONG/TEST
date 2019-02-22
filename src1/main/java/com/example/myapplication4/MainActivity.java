package com.example.myapplication4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap bitmap;
        ImageView imge=(ImageView)findViewById(R.id.image);
        imge.setImageResource(R.drawable.b);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.b);
        // 使用外部图片
        // 图片路径
       // String extraPath = "";
       // bitmap=BitmapFactory.decodeFile(extraPath);
        bitmap = fanse(bm);
        imge.setImageBitmap(bitmap);

    }

    public Bitmap fanse(Bitmap map) {
        //得到图形的宽度和长度
        int width = map.getWidth();
        int height =map.getHeight();
        //创建二值化图像
        Bitmap binarymap = null;
        binarymap = map.copy(Bitmap.Config.ARGB_8888, true);
        //依次循环，对图像的像素进行处理
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //得到当前像素的值
                int col = binarymap.getPixel(i, j);
                //得到alpha通道的值
                int alpha = col & 0xFF000000;
                //得到图像的像素RGB的值
                int red = (col & 0x00FF0000) >> 16;
                int green = (col & 0x0000FF00) >> 8;
                int blue = (col & 0x000000FF);
                // 用公式X = 0.3×R+0.59×G+0.11×B计算出X代替原来的RGB
                int gray = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                //对图像进行二值化处理
                if (gray <= 95) {
                    gray = 0;
                } else {
                    gray = 255;
                }
                // 新的ARGB
                int newColor = alpha | (gray << 16) | (gray << 8) | gray;
                //设置新图像的当前像素值
                binarymap.setPixel(i, j, 0xFFFFFF - col);
            }
        }
        return binarymap;
    }

}