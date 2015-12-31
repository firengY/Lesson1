package com.firengy.lesson1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firengy.lesson1.R;
import com.firengy.lesson1.entity.CommentItem;
import com.firengy.lesson1.tools.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by firengy
 * on 15-12-31.
 * Email: 18811372352@163.com
 */
public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentItem.ItemsEntity> list;

    public CommentAdapter(Context context) {
        this.context = context;
        this.list = new LinkedList<CommentItem.ItemsEntity>();
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
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_list_comment, parent, false);
        }
        convertView.setTag(new ViewHolder(convertView));

        CommentItem.ItemsEntity item = list.get(position);

        ViewHolder holder = (ViewHolder) convertView.getTag();

        if (item.getUser() != null) {
            holder.userName.setText(item.getUser().getLogin());
            String iconStr = item.getUser().getIcon();
            if (iconStr.equals("")) {
                iconStr = "nopic.jpg";
            }

            Picasso.with(context)
                    .load(getIconURL(item.getUser().getId(),
                            iconStr))
                    .transform(new CircleTransformation())
                    .resize(100, 100)
                    .into(holder.userIcon);

        } else {
            holder.userName.setText("匿名用户");
            holder.userName.setTextColor(Color.rgb(200, 200, 200));
            holder.userIcon.setImageResource(R.mipmap.ic_launcher);
        }
        holder.userContent.setText(item.getContent());

        return convertView;
    }

    public static String getIconURL(long id, String icon) {
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }

    public void addAll(Collection<? extends CommentItem.ItemsEntity> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        private ImageView userIcon;
        private TextView userName;
        private TextView userContent;

        public ViewHolder(View itemView) {
            userIcon = (ImageView) itemView.findViewById(R.id.icon_item_comment);
            userName = (TextView) itemView.findViewById(R.id.name_item_comment);
            userContent = (TextView) itemView.findViewById(R.id.content_item_comment);
        }
    }
}
