package com.cxyliuyu.www.trueimage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cxyliuyu.www.trueimage.util.ImageViewUtil;

public class MediumActivity extends AppCompatActivity {

    ImageView topLeftImageview;
    ImageView topRightImageview;
    ImageView topCenterImageview;

    ImageView bottomLeftImageview;
    ImageView bottomRightImageview;
    ImageView bottomCenterImageview;

    ImageView commonImageview1;
    ImageView commonImageview2;

    Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        topLeftImageview = (ImageView)findViewById(R.id.medium_top_left);
        topRightImageview = (ImageView)findViewById(R.id.medium_top_right);
        topCenterImageview = (ImageView)findViewById(R.id.medium_top_center);
        bottomLeftImageview = (ImageView)findViewById(R.id.medium_tab_home_icon);
        bottomRightImageview = (ImageView)findViewById(R.id.medium_tab_me_icon);
        bottomCenterImageview = (ImageView)findViewById(R.id.medium_tab_work_icon);
        commonImageview1 = (ImageView)findViewById(R.id.medium_common1);
        commonImageview2 = (ImageView)findViewById(R.id.medium_common2);
        refreshButton = (Button)findViewById(R.id.medium_refresh_button);

        refreshView();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshView();
            }
        });
    }

    private void refreshView(){
        String dicPath = Environment.getExternalStorageDirectory().getPath()+"/TrueImage/medium/";

        ImageViewUtil.setImageviewByUri(commonImageview1, dicPath + "common1.png");
        ImageViewUtil.setImageviewByUri(commonImageview2,dicPath+"common2.png");

        ImageViewUtil.setImageviewByUri(topLeftImageview,dicPath+"topLeft.png");
        ImageViewUtil.setImageviewByUri(topRightImageview,dicPath+"topRight.png");
        ImageViewUtil.setImageviewByUri(topCenterImageview,dicPath+"topCenter.png");
        ImageViewUtil.setImageviewByUri(bottomLeftImageview,dicPath+"bottomLeft.png");
        ImageViewUtil.setImageviewByUri(bottomRightImageview, dicPath + "bottomRight.png");
        ImageViewUtil.setImageviewByUri(bottomCenterImageview, dicPath + "bottomCenter.png");
    }
}
