package com.firengy.lesson1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firengy.lesson1.R;
import com.firengy.lesson1.entity.Response;
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
public class ZhuanXiangAdapter extends BaseAdapter {

    private Context context;
    private List<Response.ItemsEntity> list;

    public ZhuanXiangAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_list_zhuanxiang,
                    parent,
                    false);
            convertView.setTag(new ViewHolder(convertView));
        }
        Response.ItemsEntity item = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();


        if (item.getUser() != null) {
            holder.name.setText(item.getUser().getLogin());
            Picasso.with(context)
                    .load(getIconURL(item.getUser().getId(),
                            item.getUser().getIcon()))
                    .transform(new CircleTransformation())
                    .resize(100,100)
                    .into(holder.icon);
        } else {
            holder.name.setText("匿名用户");
            holder.icon.setImageResource(R.mipmap.ic_launcher);
        }
        holder.content.setText(item.getContent());
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


        return convertView;
    }

    private String getImageURL(String image) {
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        //Log.d(TAG, "getImageURL: " + matcher.group());
        return String.format(url, matcher.group(1), matcher.group(), "medium", image);

    }

    public static String getIconURL(long id, String icon) {
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }

    public void addAll(Collection<? extends Response.ItemsEntity> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        private ImageView icon;
        private TextView name;
        private TextView content;
        private ImageView image;

        public ViewHolder(View itemView) {
            icon = (ImageView) itemView.findViewById(R.id.icon_zhuanxiang);
            name = (TextView) itemView.findViewById(R.id.name_zhuanxiang);
            content = (TextView) itemView.findViewById(R.id.content_zhuanxiang);
            image = (ImageView) itemView.findViewById(R.id.image_zhuanxiang);
        }
    }
}
