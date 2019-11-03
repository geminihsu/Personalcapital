package com.gemini.personalcapital.ui.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gemini.personalcapital.R;
import com.gemini.personalcapital.WebViewActivity;
import com.gemini.personalcapital.model.Post;
import com.gemini.personalcapital.model.PostItemList;
import com.gemini.personalcapital.util.SettingsManager;
import com.squareup.picasso.Picasso;

import static com.gemini.personalcapital.constant.Constant.APPEND_URL;
import static com.gemini.personalcapital.constant.Constant.ITEM_DISPLAY_IMAGE_HEIGHT;
import static com.gemini.personalcapital.constant.Constant.ITEM_DISPLAY_IMAGE_WIDTH;
import static com.gemini.personalcapital.constant.Constant.ITEM_VIEW_TYPE_HEADER;
import static com.gemini.personalcapital.constant.Constant.ITEM_VIEW_TYPE_ITEM;
import static com.gemini.personalcapital.constant.Constant.WEBSITE_ADDRESS;


public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private final PostItemList mPostList;
    private final FragmentActivity mContext;


    public PostListAdapter(FragmentActivity context, PostItemList postItemList, View header) {
        if (postItemList == null) {
            mPostList = new PostItemList();
        } else {
            mPostList = postItemList;
        }
        mContext = context;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Post item = mPostList.get(position);

        holder.mTitleView.setText(item.getTitle());
        holder.mComment.setText(item.getComments());
        holder.mDescription.setText(item.getDescription());

        //Hidden description after ​first​ ​article​
        if (getItemViewType(position) == ITEM_VIEW_TYPE_ITEM)
            holder.mDescription.setVisibility(View.GONE);

        SettingsManager settings = SettingsManager.getInstance(mContext);
        if (settings.isHighQualityPreview()) {
            Uri uri = item.getmCover();
            if (uri != null) {
                String result = uri.toString().replace("2med", "3max");
                Picasso.get().load(result).into(holder.mCoverView);
            }
        } else {
            Picasso.get().load(item.getImageUrl())
                    .resize(ITEM_DISPLAY_IMAGE_WIDTH, ITEM_DISPLAY_IMAGE_HEIGHT)
                    .into(holder.mCoverView);
        }

        //Pass the link url
        holder.mCardView_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, WebViewActivity.class);
                i.putExtra(WEBSITE_ADDRESS, item.getLink() + APPEND_URL);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mCoverView;
        private final TextView mTitleView;
        private final TextView mComment;
        private final RelativeLayout mCardView_layout;
        private final TextView mDescription;

        ViewHolder(View view) {
            super(view);
            mCoverView = view.findViewById(R.id.cover);
            mTitleView = view.findViewById(R.id.title);
            mCardView_layout = view.findViewById(R.id.cardView_layout);
            mComment = view.findViewById(R.id.comment);
            mDescription = view.findViewById(R.id.description);
            mTitleView.setEllipsize(TextUtils.TruncateAt.END);
            mTitleView.setMaxLines(2);
            mDescription.setMaxLines(2);
            mDescription.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

}
