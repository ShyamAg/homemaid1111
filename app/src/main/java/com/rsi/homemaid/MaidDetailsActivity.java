package com.rsi.homemaid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import bean.MaidList;
import helper.RoundedImageView;

/**
 * Created by deepak.sharma on 7/20/2017.
 */

public class MaidDetailsActivity extends BaseActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ImageView iv_pic_maid, iv_favourite;

    private MaidList maid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_details);

        if (getIntent().getExtras().get("Maid") != null)
        maid = (MaidList) getIntent().getExtras().get("Maid");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(maid.getName());

        toolbarTextAppearance();

        iv_favourite = (ImageView) findViewById(R.id.iv_favourite);
        iv_pic_maid = (RoundedImageView) collapsingToolbarLayout.findViewById(R.id.iv_pic_maid);


        iv_favourite.getDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        iv_favourite.setAlpha(0.7f);

        Picasso.with(this)
                .load(maid.getPhoto())
                .into(iv_pic_maid);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
}
