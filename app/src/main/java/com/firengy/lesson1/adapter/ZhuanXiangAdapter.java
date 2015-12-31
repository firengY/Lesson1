package com.firengy.lesson1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.firengy.lesson1.CommentActivity;
import com.firengy.lesson1.R;
import com.firengy.lesson1.entity.ZhuanXiangItem;
import com.firengy.lesson1.tools.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by firengy
 * on 15-12-29.
 * Email: 18811372352@163.com
 */
public class ZhuanXiangAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<ZhuanXiangItem.ItemsEntity> list;
    private static ZhuanXiangItem.ItemsEntity item;
    private View ret;

    public ZhuanXiangAdapter(Context context) {
        this.context = context;
        list = new ArrayList<ZhuanXiangItem.ItemsEntity>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ret = null;
        if (convertView == null) {
            ret = LayoutInflater.from(context).inflate(
                    R.layout.item_list_zhuanxiang,
                    parent,
                    false);
        }
        else{
            ret = convertView;
        }
        ret.setTag(new ViewHolder(ret));

        item = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();

        if (item.getUser() != null) {
            holder.name.setText(item.getUser().getLogin());
            String iconStr = item.getUser().getIcon();
            if (iconStr.equals("")) {
                iconStr = "nopic.jpg";
            }

            Picasso.with(context)
                    .load(getIconURL(item.getUser().getId(),
                            iconStr))
                    .transform(new CircleTransformation())
                    .resize(100, 100)
                    .into(holder.icon);

        } else {
            holder.name.setText("匿名用户");
            holder.name.setTextColor(Color.rgb(200, 200, 200));
            holder.icon.setImageResource(R.mipmap.ic_launcher);
        }
        holder.content.setText(item.getContent());


        if (item.getFormat().equals("video")) {
            holder.videoPlayIcon.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.VISIBLE);
            holder.image.setAspectRatio(1.0f);
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(Uri.parse(item.getPic_url()))
                    .setProgressiveRenderingEnabled(true)
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(holder.image.getController())
                    .build();

            holder.image.setController(controller);
//            Picasso.with(context)
//                    .load(item.getPic_url())
//                    .resize(parent.getWidth(), 0)
//                    .placeholder(R.mipmap.ic_launcher)
//                    .error(R.mipmap.ic_launcher)
//                    .into(holder.image);
        } else {
            holder.videoPlayIcon.setVisibility(View.GONE);
            if (item.getImage() == null) {
                holder.image.setVisibility(View.GONE);
            } else {
                holder.image.setVisibility(View.VISIBLE);

                Picasso.with(context)
                        .load(getImageURL((String) item.getImage()))
                        .resize(parent.getWidth(), 0)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(holder.image);
            }
        }
        holder.votes.setText("好笑 " + (item.getVotes().getUp() + item.getVotes().getDown()));
        holder.commentCount.setText("评论 " + item.getComments_count());
        holder.shareCount.setText("分享 " + item.getShare_count());

        holder.clickField.setTag(position);
        holder.clickField.setOnClickListener(this);
        return ret;
    }

    //获取图片接口地址
    private String getImageURL(String image) {
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        //Log.d(TAG, "getImageURL: " + matcher.group());
        return String.format(url, matcher.group(1), matcher.group(), "medium", image);

    }

    //获取用户头像接口地址
    public static String getIconURL(long id, String icon) {
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }

    //加载更多，并刷新Adapter
    public void addAll(Collection<? extends ZhuanXiangItem.ItemsEntity> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    //item页面点击跳转commentActivity点击事件处理
    //主要是传值操作
    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Intent intent = new Intent(context, CommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",  list.get(position));
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public View getItemView(){
        return getView(0,null,null);
    }

    private static class ViewHolder {
        private final LinearLayout clickField;
        private ImageView videoPlayIcon;
        private ImageView icon;
        private TextView name;
        private TextView content;
        private SimpleDraweeView image;
        private TextView votes;
        private TextView commentCount;
        private TextView shareCount;

        public ViewHolder(View itemView) {
            icon = (ImageView) itemView.findViewById(R.id.icon_zhuanxiang);
            name = (TextView) itemView.findViewById(R.id.name_zhuanxiang);
            content = (TextView) itemView.findViewById(R.id.content_zhuanxiang);
            image = (SimpleDraweeView) itemView.findViewById(R.id.image_zhuanxiang);
            votes = (TextView) itemView.findViewById(R.id.votes_zhuanxiang);
            commentCount = (TextView) itemView.findViewById(R.id.comment_count_zhuanxiang);
            shareCount = (TextView) itemView.findViewById(R.id.share_count_zhuanxiang);
            videoPlayIcon = (ImageView) itemView.findViewById(R.id.video_play_zhuanxiang);
            clickField = (LinearLayout) itemView.findViewById(R.id.click_zhuanxiang);

        }
    }
}
