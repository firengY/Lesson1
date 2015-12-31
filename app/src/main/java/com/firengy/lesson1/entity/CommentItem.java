package com.firengy.lesson1.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by firengy
 * on 15-12-31.
 * Email: 18811372352@163.com
 */
public class CommentItem {

    /**
     * count : 30
     * items : [{"liked":false,"floor":1,"created_at":1451488579,"content":"哈哈  \u0001[嘿嘿]","like_count":2,"user":{"last_visited_at":1445902577,"created_at":1445902577,"last_device":"ios_8.0.15","state":"active","role":"n","login":"约翰·克里斯朵夫","id":30523764,"icon":"20151210121901.jpg"},"id":346486342},{"liked":false,"floor":2,"created_at":1451488646,"content":"一楼二楼好帅","like_count":1,"user":{"last_visited_at":1445902577,"created_at":1445902577,"last_device":"ios_8.0.15","state":"active","role":"n","login":"约翰·克里斯朵夫","id":30523764,"icon":"20151210121901.jpg"},"id":346486426},{"liked":false,"floor":3,"created_at":1451489140,"content":"哈哈","like_count":0,"user":{"last_visited_at":1405228274,"created_at":1405228274,"last_device":"android_3.0.3","state":"active","role":"n","login":"七色彩泥","id":17841149,"icon":"20151204221233.jpg"},"id":346487006},{"liked":false,"floor":4,"created_at":1451489519,"content":"哈哈，这尼玛回复得好","like_count":4,"user":{"last_visited_at":1416077836,"created_at":1416077836,"last_device":"ios_3.3.2","state":"active","role":"n","login":"Remember丶J","id":22736715,"icon":"20141124081034.jpg"},"id":346487548},{"liked":false,"floor":5,"created_at":1451489562,"content":"坟贴前排","like_count":0,"user":{"last_visited_at":1369059212,"created_at":1369059212,"last_device":"android_2.4","state":"active","role":"n","login":"温瞳ぶ","id":9108569,"icon":"20130520221917.jpg"},"id":346487604},{"liked":false,"floor":6,"created_at":1451489761,"content":"现在就是一条狗","like_count":1,"user":{"last_visited_at":1402065615,"created_at":1402065615,"last_device":"android_2.8.5","state":"bonding","role":"n","login":"孩子撸管不射，","id":16738247,"icon":"20150905114739.jpg"},"id":346487841},{"liked":false,"floor":7,"created_at":1451490581,"content":"哈哈，","like_count":0,"user":{"last_visited_at":1451416845,"created_at":1451416845,"last_device":"android_9.0.0","state":"active","role":"n","login":"我是你小妹","id":30869524,"icon":"20151229192045.jpg"},"id":346488906},{"liked":false,"floor":8,"created_at":1451491249,"content":"笑了","like_count":0,"user":{"last_visited_at":1447969605,"created_at":1447969605,"last_device":"android_8.3.3","state":"active","role":"n","login":"起只名好难","id":30648067,"icon":"20151227071812.jpg"},"id":346489686},{"liked":false,"floor":9,"created_at":1451494575,"content":"楼下接翔","like_count":0,"user":{"last_visited_at":1396113676,"created_at":1396113676,"last_device":"ios_2.7","state":"bonding","role":"n","login":"【夜袭寡妇村）","id":15168870,"icon":"20151216002720.jpg"},"id":346492683},{"liked":false,"floor":10,"created_at":1451495672,"content":"呵呵","like_count":0,"user":{"last_visited_at":1366603203,"created_at":1366603203,"last_device":"ios_2.3.4","state":"active","role":"n","login":"山东菏泽女汉子","id":8524348,"icon":"20151031214642.jpg"},"id":346493447},{"liked":false,"floor":11,"created_at":1451496538,"content":"1楼和2楼就是那2B","like_count":0,"user":{"last_visited_at":1427099344,"created_at":1427099344,"last_device":"ios_6.3.1","state":"active","role":"n","login":"阳山花式撸管王","id":26897432,"icon":"20151210015954.jpg"},"id":346493962},{"liked":false,"floor":12,"created_at":1451505762,"content":"哈哈。","like_count":0,"user":{"last_visited_at":1435301971,"created_at":1435301971,"last_device":"web","state":"bonded","role":"n","login":"Palliator","id":29140550,"icon":"20151224194550.jpg"},"id":346496454},{"liked":false,"floor":13,"created_at":1451506939,"content":"下辈子直接拉你到广西玉林","like_count":0,"user":{"last_visited_at":1416938424,"created_at":1416938424,"last_device":"ios_3.3.2","state":"active","role":"n","login":".9527.","id":23136667,"icon":"20150304085556.jpg"},"id":346496655},{"liked":false,"floor":14,"created_at":1451509315,"content":"哈哈","like_count":0,"user":{"last_visited_at":1330516208,"created_at":1330516208,"last_device":"ios_1.0.2","state":"active","role":"n","login":"871692380","id":873929,"icon":"20120418194730.jpg"},"id":346497032},{"liked":false,"floor":15,"created_at":1451512211,"content":"一楼二楼是条狗","like_count":0,"user":{"last_visited_at":1413954064,"created_at":1413954064,"last_device":"android_3.4.0","state":"active","role":"n","login":"\u2026\u2026因为\u2026\u2026所以\u2026\u2026","id":21959457,"icon":"20141214061124.jpg"},"id":346497527},{"liked":false,"floor":16,"created_at":1451515611,"content":"赞另一个朋友。","like_count":0,"user":{"last_visited_at":1390033815,"created_at":1390033815,"last_device":"android_2.6.4","state":"bonded","role":"n","login":"圣金子","id":13541376,"icon":"20140503215318.jpg"},"id":346498552},{"liked":false,"floor":17,"created_at":1451515653,"content":"最后一句是我万万没想到的\u0001[笑着流泪]","like_count":0,"user":{"last_visited_at":1396526125,"created_at":1396526125,"last_device":"android_2.8.1","state":"active","role":"n","login":"Hey请叫我瘋子","id":15288452,"icon":"20151227194520.jpg"},"id":346498572},{"liked":false,"floor":18,"created_at":1451515677,"content":"别下辈子了，现在也行呀","like_count":0,"user":{"last_visited_at":1357188751,"created_at":1357188751,"last_device":"android_2.1.2","state":"active","role":"n","login":"丿人火炎焱燚","id":5555263,"icon":"20131228181010.jpg"},"id":346498586},{"liked":false,"floor":19,"created_at":1451517038,"content":"好朋友好基友好丽友","like_count":0,"user":{"last_visited_at":1414047457,"created_at":1414047457,"last_device":"android_3.4.0","state":"active","role":"n","login":"说话未曾讲！","id":21993561,"icon":"20150516182540.jpg"},"id":346499237},{"liked":false,"floor":20,"created_at":1451518749,"content":"这狗疯了","like_count":0,"user":{"last_visited_at":1407071136,"created_at":1407071136,"last_device":"android_3.0.2","state":"active","role":"n","login":"放屁蹦出屎。。","id":18715995,"icon":"20150512224928.jpg"},"id":346500265},{"liked":false,"floor":21,"created_at":1451520299,"content":"妈蛋 在吃早饭看这条 噎住了","like_count":0,"user":{"last_visited_at":1374401991,"created_at":1374401991,"last_device":"android_2.5.2_6","state":"bonding","role":"n","login":"千里草一人青","id":10156435,"icon":"20140719104746.jpg"},"id":346501347},{"liked":false,"floor":22,"created_at":1451521176,"content":"真相呀~","like_count":0,"user":{"last_visited_at":1423569512,"created_at":1423569512,"last_device":"android_6.0.1","state":"active","role":"n","login":"订阅号:笑笑汇","id":25550440,"icon":"nopic.jpg"},"id":346502091},{"liked":false,"floor":23,"created_at":1451521334,"content":"就怕你还没有到找母狗来一发的年龄就成了别人盘中餐！","like_count":5,"user":{"last_visited_at":1446429089,"created_at":1446429089,"last_device":"ios_8.1.8","state":"active","role":"n","login":"无情小萌萌","id":30553919,"icon":"20151102015129.jpg"},"id":346502307},{"liked":false,"floor":24,"created_at":1451521442,"content":"坟。。。。","like_count":0,"user":{"last_visited_at":1445260954,"created_at":1445260954,"last_device":"android_8.2.1","state":"active","role":"n","login":"摔倒的123","id":30480288,"icon":"20151019132234.jpg"},"id":346502440},{"liked":false,"floor":25,"created_at":1451521496,"content":"分享该糗事到糗友圈","like_count":0,"user":{"last_visited_at":1401006459,"created_at":1401006459,"last_device":"android_2.8.7","state":"active","role":"n","login":"发舞台","id":16477221,"icon":"20140916181325.jpg"},"id":346502503},{"liked":false,"floor":26,"created_at":1451522201,"content":"然后冬天就被毒狗的射死了","like_count":0,"user":{"last_visited_at":1437680862,"created_at":1437680862,"last_device":"ios_6.9.6","state":"active","role":"n","login":"静静3333","id":29548676,"icon":""},"id":346503217},{"liked":false,"floor":27,"created_at":1451522297,"content":"多有钱人来说，现在已经在吃屎了","like_count":0,"user":{"last_visited_at":1415701814,"created_at":1415701814,"last_device":"android_3.4.2","state":"active","role":"n","login":"会打架才叫女汉子","id":22583063,"icon":"20141222220413.jpg"},"id":346503320},{"liked":false,"floor":28,"created_at":1451523137,"content":"三楼妹纸很漂亮","like_count":0,"user":{"last_visited_at":1434169044,"created_at":1434169044,"last_device":"android_7.0.1","state":"active","role":"n","login":"一只大笨熊的故事","id":28943122,"icon":"20150811144956.jpg"},"id":346504162},{"liked":false,"floor":29,"created_at":1451523196,"content":"想吃屎，想日狗，就直说！！别不好意思","like_count":0,"user":{"last_visited_at":1447513724,"created_at":1447513724,"last_device":"android_8.3.3","state":"active","role":"n","login":"俺是孤狼","id":30617667,"icon":"20151217154443.jpg"},"id":346504221},{"liked":false,"floor":30,"created_at":1451523200,"content":"这就是我想要的生活","like_count":0,"user":{"last_visited_at":1387766833,"created_at":1387766833,"last_device":"android_2.6.4","state":"active","role":"n","login":"不想不想相亲","id":13026237,"icon":"20140809155140.jpg"},"id":346504225}]
     * total : 68
     * page : 1
     * err : 0
     */

    @SerializedName("page")
    private int page;
    /**
     * liked : false
     * floor : 1
     * created_at : 1451488579
     * content : 哈哈  [嘿嘿]
     * like_count : 2
     * user : {"last_visited_at":1445902577,"created_at":1445902577,"last_device":"ios_8.0.15","state":"active","role":"n","login":"约翰·克里斯朵夫","id":30523764,"icon":"20151210121901.jpg"}
     * id : 346486342
     */

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

    public static class ItemsEntity {
        @SerializedName("floor")
        private int floor;
        @SerializedName("content")
        private String content;
        /**
         * last_visited_at : 1445902577
         * created_at : 1445902577
         * last_device : ios_8.0.15
         * state : active
         * role : n
         * login : 约翰·克里斯朵夫
         * id : 30523764
         * icon : 20151210121901.jpg
         */

        private UserEntity user;

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public int getFloor() {
            return floor;
        }

        public String getContent() {
            return content;
        }

        public UserEntity getUser() {
            return user;
        }

        public static class UserEntity {
            @SerializedName("login")
            private String login;
            @SerializedName("icon")
            private String icon;
            @SerializedName("id")
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getLogin() {
                return login;
            }

            public String getIcon() {
                return icon;
            }
        }
    }
}
