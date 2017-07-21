package adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsi.homemaid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bean.MaidList;
import helper.RoundedImageView;

/**
 * Created by deepak.sharma on 7/18/2017.
 */

public class MaidListRecyclerAdapter extends RecyclerView.Adapter<MaidListRecyclerAdapter.MyViewHolder> {

    private List<MaidList> maidLists;
    private Context context;
    private final OnItemClickListener listener;
    public MaidListRecyclerAdapter(Context context, List<MaidList> maidLists, OnItemClickListener listener) {
        this.maidLists = maidLists;
        this.context = context;
        this.listener = listener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_maid_list_row_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.bind(maidLists.get(position), listener);
        holder.setIsRecyclable(false);
        holder.tv_name.setText(maidLists.get(position).getName());
        holder.tv_religion.setText(maidLists.get(position).getReligion());
        holder.tv_rating.setText(maidLists.get(position).getRating());
        if (maidLists.get(position).getVerification().equalsIgnoreCase("Verified"))
            holder.tv_verified.setText(maidLists.get(position).getVerification());
        else
            holder.tv_verified.setVisibility(View.GONE);
        holder.tv_rs.setText(context.getResources().getString(R.string.Rs) + " " + maidLists.get(position).getCost() + " per person/month");
        holder.tv_experience.setText("Exp : " + maidLists.get(position).getExperience());
        holder.tv_available_time.setText(Html.fromHtml(maidLists.get(position).getWorkTime().replace("\\n", "\n")));

        Picasso.with(context)
                .load(maidLists.get(position).getPhoto())
                .into(holder.iv_pic_maid);
        if (maidLists.get(position).getCookingType().equalsIgnoreCase("veg")){
            holder.iv_veg.getDrawable().setColorFilter(context.getResources().getColor(R.color.darkgreen), PorterDuff.Mode.SRC_IN);
            holder.iv_veg.getBackground().setColorFilter(context.getResources().getColor(R.color.darkgreen), PorterDuff.Mode.SRC_IN);
        }else if (maidLists.get(position).getCookingType().equalsIgnoreCase("Non-Veg")){
            holder.iv_veg.getDrawable().setColorFilter(context.getResources().getColor(R.color.dark_red_color), PorterDuff.Mode.SRC_IN);
            holder.iv_veg.getBackground().setColorFilter(context.getResources().getColor(R.color.dark_red_color), PorterDuff.Mode.SRC_IN);
        }else if (maidLists.get(position).getCookingType().equalsIgnoreCase("Both")){
            holder.iv_veg.getDrawable().setColorFilter(context.getResources().getColor(R.color.orange), PorterDuff.Mode.SRC_IN);
            holder.iv_veg.getBackground().setColorFilter(context.getResources().getColor(R.color.orange), PorterDuff.Mode.SRC_IN);
        }


        if (maidLists.get(position).isFav()){
           holder.iv_favourite.getDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
           holder.iv_favourite.setAlpha(0.7f);
        }else {
            holder.iv_favourite.getDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            holder.iv_favourite.setAlpha(0.7f);
        }


        holder.iv_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maidLists.get(position).isFav()){
                    holder.iv_favourite.getDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                    holder.iv_favourite.setAlpha(0.7f);
                    maidLists.get(position).setFav(false);
                }else {
                    holder.iv_favourite.getDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    holder.iv_favourite.setAlpha(0.7f);
                    maidLists.get(position).setFav(true);
                }
            }
        });

        holder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("tel:" + maidLists.get(position).getPhoneNumber()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return maidLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView iv_pic_maid;
        public ImageView iv_favourite, iv_veg, iv_non_veg;
        public TextView tv_name, tv_religion, tv_rating, tv_rs, tv_experience, tv_available_time, tv_verified ;
        Button btn_call;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_religion = (TextView) view.findViewById(R.id.tv_religion);
            tv_rating = (TextView) view.findViewById(R.id.tv_rating);
            tv_rs = (TextView) view.findViewById(R.id.tv_rs);
            tv_experience = (TextView) view.findViewById(R.id.tv_experience);
            tv_available_time = (TextView) view.findViewById(R.id.tv_available_time_text);
            iv_pic_maid = (RoundedImageView) view.findViewById(R.id.iv_pic_maid);
            tv_verified = (TextView) view.findViewById(R.id.tv_verified);
            iv_favourite = (ImageView) view.findViewById(R.id.iv_favourite);
            iv_non_veg = (ImageView) view.findViewById(R.id.iv_non_veg);
            iv_veg = (ImageView) view.findViewById(R.id.iv_veg);
            btn_call = (Button) view.findViewById(R.id.btn_call);
        }

        public void bind(final MaidList item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnItemClickListener {
        void onItemClick(MaidList item);
    }

}
