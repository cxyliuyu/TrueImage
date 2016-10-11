package com.cxyliuyu.www.trueimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    LinearLayout mainLinearLayout;
    File dir;
    Button refreshButton;
    TextView screenSizeTextView;
    Button toSmallButton;
    Button toMediumButton;
    Button toBigButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLinearLayout = (LinearLayout)findViewById(R.id.main_linearlayout);
        refreshButton = (Button)findViewById(R.id.refresh_button);
        screenSizeTextView = (TextView)findViewById(R.id.screen_size_textview);
        toSmallButton = (Button)findViewById(R.id.to_small_button);
        toMediumButton = (Button)findViewById(R.id.to_medium_button);
        toBigButton = (Button)findViewById(R.id.to_big_button);
        ButtonOnClickListener listener = new ButtonOnClickListener(MainActivity.this);
        toSmallButton.setOnClickListener(listener);
        toMediumButton.setOnClickListener(listener);
        toBigButton.setOnClickListener(listener);
        Log.i("123124","程序启动");
        //初始化存放文件的文件夹
        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
        Log.i("123124",sdCardPath);
        final String dicPath = sdCardPath+"/TrueImage/";
        dir = new File(dicPath);
        if(!dir.exists()) {
            dir.mkdir();
            File smallDir= new File(dicPath+"/small");
            File mediumDir= new File(dicPath+"/medium");
            File bigDir= new File(dicPath+"/big");
            smallDir.mkdir();
            mediumDir.mkdir();
            bigDir.mkdir();
            Toast.makeText(MainActivity.this,"创建文件夹成功",Toast.LENGTH_LONG).show();
            Log.i("123124", "文件夹不存在");
        }
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshView(dicPath);
            }
        });
        refreshView(dicPath);
    }

    private void refreshView(String dicPath){
        Log.i("123124", "刷新页面");
        Vector<String> imageVector;
        //清空当前页面
        mainLinearLayout.removeAllViews();
        if(dir.exists()){
            //遍历文件夹
            imageVector = new Vector<>();
            File[] subFile = dir.listFiles();
            for(int i=0; i<subFile.length;i++){
                //判断是否为文件夹
                if(!(subFile[i].isDirectory())){
                    String fileName = subFile[i].getName();
                    if(fileName.trim().toLowerCase().endsWith(".png")){
                        imageVector.add(fileName);
                    }
                }
            }

            for(int i=0;i<imageVector.size();i++){
                String imgPath = dicPath +imageVector.get(i).toString();
                String fileName = imageVector.get(i).toString();
                Bitmap bitmapImg = BitmapFactory.decodeFile(imgPath);
                int width = bitmapImg.getWidth();
                int height = bitmapImg.getHeight();
                //Log.i("123124", "width = " + width);
                //Log.i("123124", "height = " + height);
                LinearLayout imageLayout = new LinearLayout(MainActivity.this);
                ImageView imageView = new ImageView(MainActivity.this);
                TextView imageNameTextView = new TextView(MainActivity.this);
                TextView imageSizeTextView = new TextView(MainActivity.this);
                imageLayout.addView(imageView);
                imageLayout.addView(imageNameTextView);
                imageLayout.addView(imageSizeTextView);
                mainLinearLayout.addView(imageLayout);
                imageLayout.setOrientation(LinearLayout.VERTICAL);
                imageView.setImageBitmap(bitmapImg);
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.width = width;
                params.height = height;
                imageView.setLayoutParams(params);
                imageNameTextView.setText("文件名：" + fileName);
                imageSizeTextView.setText("尺 寸："+width+"x"+height);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,5,0,20);
                imageLayout.setLayoutParams(lp);
            }

        }
        //获取屏幕大小
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int screenWidth = metric.widthPixels;  // 屏幕宽度（像素）
        int screenHeight = metric.heightPixels;  // 屏幕高度（像素）
        screenSizeTextView.setText("屏幕尺寸："+screenWidth+"x"+screenHeight);
        Toast.makeText(MainActivity.this,"刷新成功",Toast.LENGTH_LONG).show();
    }

    class ButtonOnClickListener implements View.OnClickListener{

        private Context context;

        public ButtonOnClickListener(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.to_small_button:
                    Intent intent1 = new Intent(context,SmallActivity.class);
                    context.startActivity(intent1);
                    break;
                case R.id.to_medium_button:
                    Intent intent2 = new Intent(context,MediumActivity.class);
                    context.startActivity(intent2);
                    break;
                case R.id.to_big_button:
                    Intent intent3 = new Intent(context,BigActivity.class);
                    context.startActivity(intent3);
                    break;
            }
        }
    }



}
