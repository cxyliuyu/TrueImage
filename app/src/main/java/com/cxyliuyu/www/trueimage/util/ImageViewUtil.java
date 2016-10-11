package com.cxyliuyu.www.trueimage.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by ly on 2016/10/11.
 */
public class ImageViewUtil {
    public static void setImageviewByUri(ImageView imageView,String uri){
        File file = new File(uri);
        imageView.setImageDrawable(null);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(uri);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = height;
            params.width = width;
            imageView.setLayoutParams(params);
            imageView.setImageBitmap(bitmap);
            imageView.setBackgroundColor(0);
        }
    }
}
