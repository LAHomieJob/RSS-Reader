package com.fulldive.reader;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fulldive.reader.ui.viewmodels.ViewModel;
import com.fulldive.reader.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String title;
    private String link;
    private ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recieveIntent();
        initToolbar();
        initDetailOfNews();
    }

    private void recieveIntent() {
        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
    }

    private void initToolbar() {
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);
    }

    private void initDetailOfNews() {
        ImageView detailImage = findViewById(R.id.image_detail);
        Button buttonWatchMore = findViewById(R.id.button_watch_more);
        buttonWatchMore.setOnClickListener(this);
        TextView titleTextView = findViewById(R.id.title_detail);
        titleTextView.setText(title);
        mViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        mViewModel.getNewsEntityByTitle(title).observe(this, newsEntity -> {
            link = newsEntity.getLink();
            String description = newsEntity.getDescription();
            String imageUrl = Utilities.parseDescriptionForImageUrl(description);
            Picasso.get().load(imageUrl)
                    .error(R.drawable.tech_crunch_logo)
                    .into(detailImage);
        });
    }

    @Override
    public void onClick(final View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        if (browserIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            startActivity(browserIntent);
        }
    }
}
