package com.rsi.homemaid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import adapter.MaidListRecyclerAdapter;
import bean.MaidDataClass;
import bean.MaidList;
import helper.RecyclerTouchListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by deepak.sharma on 7/18/2017.
 */

public class MaidListActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MaidListRecyclerAdapter maidListRecyclerAdapter;
    private List<MaidList> maidLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Call<MaidDataClass> call = apiService.getMaidsList(getMaidsJson(getIntent().getExtras().getString("CatId")).toString());
        mProgressDialog.show();
        call.enqueue(new Callback<MaidDataClass>() {
            @Override
            public void onResponse(Call<MaidDataClass> call, Response<MaidDataClass> response) {

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.body().getStatus().equalsIgnoreCase("success")){

                    maidLists = response.body().getMaidList();
                    maidListRecyclerAdapter = new MaidListRecyclerAdapter(MaidListActivity.this, response.body().getMaidList(), new MaidListRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(MaidList item) {
                            startActivity(new Intent(MaidListActivity.this, MaidDetailsActivity.class));
                        }
                    });
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(maidListRecyclerAdapter);
                }
            }
            @Override
            public void onFailure(Call<MaidDataClass> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                showCustomToast(recyclerView, getString(R.string.err_message_retrofit));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.action_fav){
            startActivity(new Intent(MaidListActivity.this, FavoriteActivity.class));
            return true;
        }

        if(id == R.id.action_filter){
            startActivity(new Intent(MaidListActivity.this, FilterActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected JSONArray getMaidsJson(String categoryId) {
        JSONObject paramObject = new JSONObject();
        JSONArray jsonArray =new JSONArray();
        try {
            paramObject.put("categoryId", categoryId);
            jsonArray.put(paramObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

}
