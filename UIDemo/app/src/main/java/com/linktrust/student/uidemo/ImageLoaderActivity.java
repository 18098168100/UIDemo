package com.linktrust.student.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.linktrust.student.uidemo.ImageLoader.NeGlide;

public class ImageLoaderActivity extends AppCompatActivity {
    private String imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590397813458&di=dd40c588361c226229f21d82fd0a872c&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F94o3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Feaf81a4c510fd9f95f48a24b212dd42a2834a4b1.jpg";
    private ScrollView scroll_line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        Button load_one_btn = findViewById(R.id.load_one_btn);
        Button load_more_btn = findViewById(R.id.load_more_btn);
        scroll_line = findViewById(R.id.scroll_line);
        load_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigle();
            }
        });


        load_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadmore();
            }
        });




    }

    private void loadmore() {
//        for (int i = 0; i < 2; i++) {
//            imagePath = null;
//            ImageView imageView = new ImageView(this);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            scroll_line.addView(imageView);
//            switch (i){
//                case 0:
//                    imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590397813458&di=dd40c588361c226229f21d82fd0a872c&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F94o3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Feaf81a4c510fd9f95f48a24b212dd42a2834a4b1.jpg";
//                    break;
//                case 1:
//                    imagePath = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1141259048,554497535&fm=26&gp=0.jpg";
//
//                    break;
//                case 2:
//                    imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590400308376&di=f4606483cf56bbf2b91463ac6a6a9bb6&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F35%2F34%2F19300001295750130986345801104.jpg";
//
//                    break;
//                case 3:
//                    imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590400308375&di=2ec0df6e4731e4f6d910b7e3372f8ff4&imgtype=0&src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0903%2F7512230abc4724321254dcac513c6ec8.jpg";
//
//                    break;
//                    default:
//                        break;
//            }
//            //Glide.with(this).load(imagePath).into(imageView);
//            NeGlide.with(this).load(imagePath).loading(R.mipmap.ic_launcher_new).into(imageView);
//        }

        ImageView imageView1 = findViewById(R.id.img1);
        ImageView imageView2 = findViewById(R.id.img2);
        ImageView imageView3 = findViewById(R.id.img3);
        ImageView imageView4 = findViewById(R.id.img4);
        ImageView imageView5 = findViewById(R.id.img5);
        ImageView imageView6 = findViewById(R.id.img6);
        ImageView imageView7 = findViewById(R.id.img7);
        ImageView imageView8 = findViewById(R.id.img8);
        ImageView imageView9 = findViewById(R.id.img9);
        ImageView imageView10 = findViewById(R.id.img10);

        String imagePath1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590397813458&di=dd40c588361c226229f21d82fd0a872c&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F94o3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Feaf81a4c510fd9f95f48a24b212dd42a2834a4b1.jpg";

        String imagePath2 = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1141259048,554497535&fm=26&gp=0.jpg";

        String imagePath3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590400308376&di=f4606483cf56bbf2b91463ac6a6a9bb6&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F35%2F34%2F19300001295750130986345801104.jpg";

        String imagePath4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590400308375&di=2ec0df6e4731e4f6d910b7e3372f8ff4&imgtype=0&src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0903%2F7512230abc4724321254dcac513c6ec8.jpg";

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath2).into(imageView1);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath4).into(imageView2);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath2).into(imageView3);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath3).into(imageView4);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath4).into(imageView5);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath1).into(imageView6);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath2).into(imageView7);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath3).into(imageView8);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath4).into(imageView9);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath2).into(imageView10);


    }

    public void sigle(){
        ImageView imageView1 = findViewById(R.id.img1);

        NeGlide.with(ImageLoaderActivity.this).loading(R.mipmap.ic_launcher_new).load(imagePath).into(imageView1);
    }

}
