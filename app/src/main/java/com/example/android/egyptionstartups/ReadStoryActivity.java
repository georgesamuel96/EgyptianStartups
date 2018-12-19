package com.example.android.egyptionstartups;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ReadStoryActivity extends AppCompatActivity {

    TextView nameTextView, ceoTextView, siteTextView, storyTextView;
    ImageView imgView;
    private RequestOptions option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_story);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");
        String ceo = intent.getStringExtra("ceo");
        String story = intent.getStringExtra("story");
        String site = intent.getStringExtra("site");

        nameTextView = (TextView)findViewById(R.id.name);
        ceoTextView = (TextView)findViewById(R.id.ceo_txt);
        siteTextView = (TextView)findViewById(R.id.site_txt);
        storyTextView = (TextView)findViewById(R.id.story_txt);
        imgView = (ImageView)findViewById(R.id.img);

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        nameTextView.setText(name);
        Glide.with(ReadStoryActivity.this).load(img).apply(option).into(imgView);
        ceoTextView.setText(ceo);
        storyTextView.setText(story);
        siteTextView.setText(site);
    }
}
