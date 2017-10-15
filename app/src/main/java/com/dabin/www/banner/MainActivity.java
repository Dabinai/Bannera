package com.dabin.www.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.GlideImageLoader;
import bean.NewBase;
import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.NetWorkUtils;
import utils.OkHttp3Utils;

public class MainActivity extends AppCompatActivity {

    List mlist=new ArrayList();
    String mpath = "http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage";
    private Banner mbanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbanner = (Banner) findViewById(R.id.mybanner);


        boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(this);
        if (!netWorkAvailable) {
            Toast.makeText(MainActivity.this, "联网：" + netWorkAvailable, Toast.LENGTH_SHORT).show();
        }
        getData();
    }

    //get的请求
    private void getData() {
        OkHttp3Utils.getInstance().doGet(mpath, new GsonObjectCallback<NewBase>() {


            @Override
            public void onUi(NewBase newBase) {

                for(int i=0; i<newBase.getData().getAd1().size();i++){
                    String ad1s = newBase.getData().getAd1().get(i).getImage();
                    mlist.add(ad1s);
                }
                //设置图片加载器
                //mbanner.setImageLoader(new GlideImageLoader());
                mbanner.setImageLoader(new GlideImageLoader());
                mbanner.setImages(mlist);
                mbanner.start();
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
