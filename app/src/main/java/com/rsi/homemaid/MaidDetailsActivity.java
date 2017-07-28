package com.rsi.homemaid;

import android.content.Intent;
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
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import bean.MaidList;
import helper.RoundedImageView;
import io.techery.properratingbar.ProperRatingBar;

/**
 * Created by deepak.sharma on 7/20/2017.
 */

public class MaidDetailsActivity extends BaseActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ImageView iv_pic_maid;
    private LikeButton heart_button;
    private TextView tv_name, tv_rating, tv_rating_count, tv_address, tv_religion, tv_experience, tv_available_time, tv_veg, tv_non_veg, tv_permanent, tv_temporary, tv_currently_working ;
    private ProperRatingBar upperRatingBar;
    private LinearLayout ll_call, ll_rating, ll_share;
    private MaidList maid;
    private String star = "★";

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

        heart_button = (LikeButton) findViewById(R.id.heart_button);
        iv_pic_maid = (RoundedImageView) collapsingToolbarLayout.findViewById(R.id.iv_pic_maid);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_rating = (TextView) findViewById(R.id.tv_rating);
        tv_rating_count = (TextView) findViewById(R.id.tv_rating_count);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_religion = (TextView) findViewById(R.id.tv_religion);
        tv_experience = (TextView) findViewById(R.id.tv_experience);
        tv_available_time = (TextView) findViewById(R.id.tv_available_time);
        tv_veg = (TextView) findViewById(R.id.tv_veg);
        tv_non_veg = (TextView) findViewById(R.id.tv_non_veg);
        tv_permanent = (TextView) findViewById(R.id.tv_permanent);
        tv_temporary = (TextView) findViewById(R.id.tv_temporary);
        tv_currently_working = (TextView) findViewById(R.id.tv_currently_working);
        upperRatingBar = (ProperRatingBar) findViewById(R.id.upperRatingBar);
        ll_call = (LinearLayout) findViewById(R.id.ll_call);
        ll_rating = (LinearLayout) findViewById(R.id.ll_rating);
        ll_share = (LinearLayout) findViewById(R.id.ll_share);






        heart_button.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });

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

        FloatingActionButton fab_call = (FloatingActionButton) findViewById(R.id.fab);
        fab_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_name.setText(maid.getName());
        tv_rating.setText(maid.getRating());
        tv_address.setText(maid.getWorkLocation());
        tv_religion.setText(maid.getReligion());
        tv_experience.setText(maid.getExperience());
        tv_available_time.setText(Html.fromHtml(maid.getWorkTime().replace("\\n", "\n")));
        if (maid.getCookingType().equalsIgnoreCase("Veg")){
            tv_veg.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_tick ), null, null, null);
            tv_non_veg.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_cross ), null, null, null);
        }else if (maid.getCookingType().equalsIgnoreCase("Non-Veg")){
            tv_veg.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_cross ), null, null, null);
            tv_non_veg.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_tick ), null, null, null);
        }else if (maid.getCookingType().equalsIgnoreCase("Both")){
            tv_veg.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_tick ), null, null, null);
            tv_non_veg.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_tick ), null, null, null);
        }


        upperRatingBar.setRating((int)Float.parseFloat(maid.getRating()));

    }

    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
}
