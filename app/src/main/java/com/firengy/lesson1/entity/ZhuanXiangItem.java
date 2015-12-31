package com.firengy.lesson1.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by firengy
 * on 15-12-29.
 * Email: 18811372352@163.com
 */
public class ZhuanXiangItem implements Serializable{

    @SerializedName("page")
    private int page;
    /**
     * format : word
     * image : null
     * published_at : 1451346901
     * tag :
     * user : {"avatar_updated_at":1451392978,"last_visited_at":1327836426,"created_at":1327836426,"state":"active","email":" ","last_device":"ios_1.0","role":"n","login":"潇湘墨兰","id":441420,"icon":"20151229124258.jpg"}
     * image_size : null
     * id : 114462277
     * votes : {"down":-490,"up":19350}
     * created_at : 1451344564
     * content : 见学长夫妇，说是最近俩人一直分床睡，已经好几个月了。我还以为是关系冷淡期，关切地问怎么回事。学长含羞一笑，说俩人共同爱好太多，三观太和，永远有聊不完的话题，一不小心就唠到三四点，再愉快地啪啪一下，第二天根本没法起床上班...秀得我泪流满面…
     * state : publish
     * comments_count : 187
     * allow_comment : true
     * share_count : 692
     * type : hot
     */

    @SerializedName("items")
    private List<ItemsEntity> items;

    public void setPage(int page) {
        this.page = page;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public List<ItemsEntity> getItems() {
        return items;
    }

    public static class ItemsEntity implements Serializable{
        @SerializedName("format")
        private String format;
        @SerializedName("image")
        private Object image;
        /**
         * avatar_updated_at : 1451392978
         * last_visited_at : 1327836426
         * created_at : 1327836426
         * state : active
         * email :
         * last_device : ios_1.0
         * role : n
         * login : 潇湘墨兰
         * id : 441420
         * icon : 20151229124258.jpg
         */

        @SerializedName("user")
        private UserEntity user;
        @SerializedName("id")
        private int id;
        /**
         * down : -490
         * up : 19350
         */

        @SerializedName("votes")
        private VotesEntity votes;
        @SerializedName("content")
        private String content;
        @SerializedName("comments_count")
        private int comments_count;
        @SerializedName("allow_comment")
        private boolean allow_comment;
        @SerializedName("share_count")
        private int share_count;
        @SerializedName("type")
        private String type;
        @SerializedName("pic_url")
        private String pic_url;

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setVotes(VotesEntity votes) {
            this.votes = votes;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public void setAllow_comment(boolean allow_comment) {
            this.allow_comment = allow_comment;
        }

        public void setShare_count(int share_count) {
            this.share_count = share_count;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFormat() {
            return format;
        }

        public Object getImage() {
            return image;
        }

        public UserEntity getUser() {
            return user;
        }

        public int getId() {
            return id;
        }

        public VotesEntity getVotes() {
            return votes;
        }

        public String getContent() {
            return content;
        }

        public int getComments_count() {
            return comments_count;
        }

        public boolean isAllow_comment() {
            return allow_comment;
        }

        public int getShare_count() {
            return share_count;
        }

        public String getType() {
            return type;
        }

        public static class UserEntity implements Serializable{
            @SerializedName("login")
            private String login;
            @SerializedName("id")
            private int id;
            @SerializedName("icon")
            private String icon;

            public void setLogin(String login) {
                this.login = login;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getLogin() {
                return login;
            }

            public int getId() {
                return id;
            }

            public String getIcon() {
                return icon;
            }
        }

        public static class VotesEntity implements Serializable{
            @SerializedName("down")
            private int down;
            @SerializedName("up")
            private int up;

            public void setDown(int down) {
                this.down = down;
            }

            public void setUp(int up) {
                this.up = up;
            }

            public int getDown() {
                return down;
            }

            public int getUp() {
                return up;
            }
        }
    }
}
