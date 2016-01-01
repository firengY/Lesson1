package com.firengy.lesson1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
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
    private CommentItem.ItemsEntity item;

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

        item = list.get(position);

        ViewHolder holder = (ViewHolder) convertView.getTag();

        setUserInfo(holder);

        holder.userContent.setText(item.getContent());

        return convertView;
    }

    //设置用户信息----头像 用户名
    private void setUserInfo(ViewHolder holder) {
        String iconStr = "nopic.jpg";
        holder.userName.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        if (item.getUser() != null) {
            //如果不是匿名用户，设置用户名颜色为黑色
            holder.userName.setText(item.getUser().getLogin());
            holder.userName.setTextColor(Color.rgb(0, 0, 0));

            //获取解析实体类Icon字段，因为存在""，所以添加判断
            iconStr = item.getUser().getIcon();
            if (iconStr.equals("")){
                //如果icon字段为""，则设置为匿名用户头像
                iconStr = "nopic.jpg";
            }

            Picasso.with(context)
                    .load(getIconURL(item.getUser().getId(),
                            iconStr))
                    .transform(new CircleTransformation())
                    .resize(80, 80)
                    .into(holder.userIcon);
        } else {
            //如果是匿名用户，设置用户名颜色为灰色，并显示其原本头像
            holder.userName.setText("匿名用户");
            holder.userName.setTextColor(Color.rgb(200, 200, 200));
            Picasso.with(context)
                    .load(R.mipmap.default_anonymous_users_avatar)
                    .transform(new CircleTransformation())
                    .resize(80,80)
                    .into(holder.userIcon);
        }
        //return iconStr;
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
