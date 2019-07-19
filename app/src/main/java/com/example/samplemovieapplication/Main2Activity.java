package com.example.samplemovieapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
TextView name;
TextView rating;
TextView views;
TextView language;
TextView genre;
ImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=(TextView)findViewById(R.id.name);
        rating=(TextView)findViewById(R.id.rating);
        views=(TextView)findViewById(R.id.views);
        language=(TextView)findViewById(R.id.language);
        genre=(TextView)findViewById(R.id.genre);
        picture=(ImageView)findViewById(R.id.imageView);
        name.setText("Name: "+getIntent().getStringExtra("filmname"));
        rating.setText("Rating: "+Double.toString(getIntent().getDoubleExtra("rating",2)));
        rating.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_star_black_24dp,0);
        language.setText("Language: "+getIntent().getStringExtra("language"));
        views.setText("Views: "+Double.toString(getIntent().getDoubleExtra("views",100))+"Views");
        genre.setText("Genre: "+getIntent().getStringExtra("genre"));
        String imageUrl=getIntent().getStringExtra("image");
        Glide.with(this).load(imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(100))).into(picture);

    }
}
