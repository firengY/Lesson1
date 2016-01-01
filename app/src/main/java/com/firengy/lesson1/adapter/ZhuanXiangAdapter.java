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
import android.widget.RadioGroup;
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
public class ZhuanXiangAdapter extends BaseAdapter implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "ZhuanXiangAdapter";

    private Context context;
    private List<ZhuanXiangItem.ItemsEntity> list;
    private static ZhuanXiangItem.ItemsEntity item;
    private View ret;
    private static int width;

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
        } else {
            ret = convertView;
        }
        ret.setTag(new ViewHolder(ret));

        item = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();

        setUserInfo(holder);

        holder.content.setText(item.getContent());

        //不同format的处理
        if (item.getFormat().equals("video")) { //format---->video
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
        } else {    //format----->image/text...
            holder.videoPlayIcon.setVisibility(View.GONE);
            if (item.getImage() == null) {
                holder.image.setVisibility(View.GONE);
            } else {
                holder.image.setVisibility(View.VISIBLE);


                //Log.d(TAG, "getView: parent = " + parent.getWidth());
                if (parent != null) {
                    width = parent.getWidth();
                }
                Picasso.with(context)
                        .load(getImageURL((String) item.getImage()))
                        .resize(width, 0)
                        .placeholder(R.mipmap.bg_light_tile)
                        .error(R.mipmap.bg_light_tile)
                        .into(holder.image);
            }
        }
        holder.votes.setText("好笑 " + (item.getVotes().getUp() + item.getVotes().getDown()));
        holder.commentCount.setText("评论 " + item.getComments_count());
        holder.shareCount.setText("分享 " + item.getShare_count());

        holder.clickField.setTag(position);
        holder.clickField.setOnClickListener(this);

        holder.comment.setOnClickListener(this);
        holder.share.setOnClickListener(this);

        holder.emotion.setOnCheckedChangeListener(this);
        return ret;
    }

    //设置用户信息-----头像 用户名
    private void setUserInfo(ViewHolder holder) {
        String iconStr = "nopic.jpg";
        if (item.getUser() != null) {
            holder.name.setText(item.getUser().getLogin());
            holder.name.setTextColor(Color.rgb(0, 0, 0));
                iconStr = item.getUser().getIcon();
            if (iconStr.equals("")) {
                iconStr = "nopic.jpg";
            }
            Picasso.with(context)
                    .load(getIconURL(item.getUser().getId(),
                            iconStr))
                    .transform(new CircleTransformation())
                    .resize(80, 80)
                    .into(holder.icon);
        } else {
            holder.name.setText("匿名用户");
            holder.name.setTextColor(Color.rgb(200, 200, 200));
            Picasso.with(context)
                    .load(R.mipmap.default_anonymous_users_avatar)
                    .resize(80,80)
                    .transform(new CircleTransformation())
                    .into(holder.icon);
        }
        //return iconStr;
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
    //评论点击事件处理
    //分享点击事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click_zhuanxiang:
                int position = Integer.parseInt(v.getTag().toString());
                Intent intent = new Intent(context, CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", list.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case R.id.comment_zhuanxiang:
                break;
            case R.id.share_zhuanxiang:
                break;

        }
    }


    //获取点击Item的view，用于在CommentActivity中复用
    public View getItemView() {
        //因为只有点击的子项，所以只有一个
        return getView(0, null, null);
    }

    //RadioGroup的checkedChanged事件处理
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //TODO: support/unsupport 事件处理
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
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

        private ImageView comment;
        private ImageView share;
        private RadioGroup emotion;

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


            comment = (ImageView) itemView.findViewById(R.id.comment_zhuanxiang);
            share = (ImageView) itemView.findViewById(R.id.share_zhuanxiang);

            emotion = (RadioGroup) itemView.findViewById(R.id.radio_emotion_zhuanxiang);

        }
    }
}
