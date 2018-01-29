package com.pgmail.martsulg.tankmods.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pgmail.martsulg.tankmods.R;
import com.pgmail.martsulg.tankmods.entity.HotFeed;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lenovo on 08.10.2017.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.Holder> {

    private Activity activity;
    private ArrayList<HotFeed> hotFeed;

    private final int TYPE_HEADER = 0;
    private final int TYPE_CARD = 1;
    private final int TYPE_RECOMMEND = 2;
    private final int TYPE_VIDEO = 3;
    private final int TYPE_COMMENT = 4;

    public HotAdapter(Activity activity, ArrayList<HotFeed> hotFeed) {
        this.activity = activity;
        this.hotFeed = hotFeed;
    }



    public static class Holder extends RecyclerView.ViewHolder{

        ImageView heroPic;
        TextView heroTitle;
        TextView heroDescr;
        TextView heroLikes;
        TextView heroDownloads;
        TextView heroAction;
        TextView commentTitle;
        TextView commentAuth;
        TextView commentCreated;
        TextView commentText;
        ImageView commentPic;

        public Holder(View itemView) {
            super(itemView);
            heroPic = itemView.findViewById(R.id.hotCard_img);
            heroTitle = itemView.findViewById(R.id.hotCard_header);
            heroDescr = itemView.findViewById(R.id.hotCard_description);
            heroLikes = itemView.findViewById(R.id.hotCard_likesCount);
            heroDownloads = itemView.findViewById(R.id.hotCard_downldsCount);
            heroAction = itemView.findViewById(R.id.hotCard_openBtn);
            commentTitle = itemView.findViewById(R.id.hot_comment_title);
            commentAuth = itemView.findViewById(R.id.hot_comment_auth);
            commentCreated = itemView.findViewById(R.id.hot_comment_created);
            commentText = itemView.findViewById(R.id.hot_comment_text);
            commentPic = itemView.findViewById(R.id.hot_comment_img);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = null;
        switch (viewType) {
            case TYPE_HEADER:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_hot_featured, parent, false);
                break;
            case TYPE_CARD:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_hot_card, parent, false);
                break;
            case TYPE_RECOMMEND:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_hot_recommended, parent, false);
                break;
            case TYPE_VIDEO:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_hot_video, parent, false);
                break;
            case TYPE_COMMENT:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_hot_comment, parent, false);
                break;
        }
        return new Holder(root);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        int type = getItemViewType(position);
        Context context= activity.getApplicationContext();

        switch (type) {

            case TYPE_HEADER:

                LinearLayout headLinear = holder.itemView.findViewById(R.id.item_hot_linear);
                headLinear.removeAllViews();
                for (int i = 0; i < hotFeed.get(position).getItems().size(); i++) {
                    LinearLayout headLayout = new LinearLayout(activity);
                    headLayout.setPadding(2, 0, 2, 0);
                    TextView header = new TextView(activity);
                    header.setText(hotFeed.get(position).getItems().get(i).getName());
                    header.setBackgroundResource(R.drawable.softgrey_roundrect);
                    header.setTextColor(context.getResources().getColor(R.color.black));
                    float size = context.getResources().getDimensionPixelOffset(R.dimen.text_16sp);
                    header.setTextSize(16);
                    int padding = context.getResources().getDimensionPixelOffset(R.dimen.dp_92);
                    header.setPadding(0,padding,0,0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            context.getResources().getDimensionPixelOffset(R.dimen.dp_116),
                            context.getResources().getDimensionPixelOffset(R.dimen.dp_141));
                    headLayout.addView(header,params);
                    headLinear.addView(headLayout);
                }
                break;

            case TYPE_CARD:
                Picasso.with(holder.heroPic.getContext())
                        .load(hotFeed.get(position).getThumbnail())
                        .into(holder.heroPic, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                            }
                        });

                holder.heroTitle.setText(hotFeed.get(position).getTitle());
                holder.heroDescr.setText(hotFeed.get(position).getCaption());
                holder.heroLikes.setText(String.valueOf(hotFeed.get(position).getLikes()));
                holder.heroDownloads.setText(String.valueOf(hotFeed.get(position).getDownloads()));
                holder.heroAction.setText(hotFeed.get(position).getCta_type());

                for (int i = 0; i < hotFeed.get(position).getTags().size(); i++) {
                    LinearLayout tagLinear = holder.itemView.findViewById(R.id.hotCard_tagLinear);
                    LinearLayout extraLayout = new LinearLayout(activity);
                    extraLayout.setPadding(2, 0, 2, 0);
                    TextView tag = new TextView(activity);
                    tag.setText(hotFeed.get(position).getTags().get(i));
                    tag.setBackgroundResource(R.drawable.darkroundrect);
                    tag.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    tag.setTextColor(context.getResources().getColor(R.color.white));
                    tag.setAllCaps(true);
                    extraLayout.addView(tag);
                    tagLinear.addView(extraLayout);
                }
                break;

            case TYPE_RECOMMEND:
                for (int i = 0; i < hotFeed.get(position).getItems().size(); i++) {
                    LinearLayout linear = holder.itemView.findViewById(R.id.hot_recommended_linear);
                    ImageView image = new ImageView(activity);
                    Picasso.with(image.getContext())
                            .load(hotFeed.get(position).getItems().get(i).getThumbnail())
                            .into(image, new Callback() {
                                @Override
                                public void onSuccess() {
                                }

                                @Override
                                public void onError() {
                                }
                            });
                    image.setBackgroundResource(R.drawable.recom_rect);
                    int padding = context.getResources().getDimensionPixelOffset(R.dimen.dp_10);
                    image.setPadding(padding, 0, padding, 0);
                    linear.addView(image);

                }

                break;
            case TYPE_VIDEO:

                break;
            case TYPE_COMMENT:
                holder.commentTitle.setText(hotFeed.get(position).getTitle());
                for(int i=0;i<hotFeed.get(position).getItems().size();i++) {
                    Picasso.with(holder.commentPic.getContext())
                            .load(hotFeed.get(position).getItems().get(i).getSubject().getThumbnail())
                            .into(holder.commentPic, new Callback() {
                                @Override
                                public void onSuccess() {
                                }

                                @Override
                                public void onError() {
                                }
                            });
                    holder.commentAuth.setText(hotFeed.get(position).getItems().get(i).getAuthor().getName());
                    holder.commentCreated.setText(hotFeed.get(position).getItems().get(i).getCreated_at());
                    holder.commentText.setText(hotFeed.get(position).getItems().get(i).getText());
                }
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (hotFeed.get(position).getType()){
            case "horizontal-list-featuring":
                return TYPE_HEADER;
            case "hero":
                return TYPE_CARD;
            case "horizontal-list":
                return TYPE_RECOMMEND;
            case "youtube-video-cta":
                return TYPE_VIDEO;
            default:
                return TYPE_COMMENT;
        }
    }

    @Override
    public int getItemCount() {
        return hotFeed.size();
    }
}
