package com.example.order002;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vin on 08/12/2016.
 */

public class Model {

    private int cId;
    private String cName;
    private List<SubModel> subModelList;


    private int badge;

    public Model(int cId, String cName, List<SubModel> subModelList) {
        this.cId = cId;
        this.cName = cName;
        this.subModelList = subModelList;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public List<SubModel> getSubModelList() {
        return subModelList;
    }

    public void setSubModelList(List<SubModel> subModelList) {
        this.subModelList = subModelList;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public static class SubModel {

        /*****tag*****/
        private int cId;
        private String cName;
        /*************/

        private int num;

        private String name;
        private double price;

        public int getcId() {
            return cId;
        }

        public void setcId(int cId) {
            this.cId = cId;
        }

        public String getcName() {
            return cName;
        }

        public void setcName(String cName) {
            this.cName = cName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getprice() {
            return price;
        }

        public void setprice(double price) {
            this.price = price;
        }

        public SubModel(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }


    public static List<Model> initData(){

        List<Model> list = new ArrayList<>();

        List<SubModel> jiamo = new ArrayList<>();
        jiamo.add(new SubModel("优质肉夹馍",12.5));
        jiamo.add(new SubModel("青椒肉夹馍",12.5));
        jiamo.add(new SubModel("纯瘦肉夹馍",13.5));
        jiamo.add(new SubModel("原味馍饼",4.5));
        jiamo.add(new SubModel("黑椒牛肉夹馍",15.5));

        List<SubModel> liangpi = new ArrayList<>();
        liangpi.add(new SubModel("秘制凉皮",10));
        liangpi.add(new SubModel("麻酱凉皮",10));

        List<SubModel> mianshi = new ArrayList<>();
        mianshi.add(new SubModel("油泼面",12));
        mianshi.add(new SubModel("油泼面加量版",15));
        mianshi.add(new SubModel("特色酸辣粉",15.6));
        mianshi.add(new SubModel("油泼臊子面",19.6));
        mianshi.add(new SubModel("岐山臊子面",19.6));
        mianshi.add(new SubModel("他舅家炸酱面",19.6));
        mianshi.add(new SubModel("红烧牛肉面",24));
        mianshi.add(new SubModel("滋补羊肉面",29.5));

        List<SubModel> zuocan = new ArrayList<>();
        zuocan.add(new SubModel("农家小酥肉",15));
        zuocan.add(new SubModel("美味盐酥鸡",12.5));
        zuocan.add(new SubModel("纯肉烤肠",6));
        zuocan.add(new SubModel("醇香豆浆",6));
        zuocan.add(new SubModel("红豆粥",6));
        zuocan.add(new SubModel("紫菜蛋花汤",5));
        zuocan.add(new SubModel("卤蛋",3));
        zuocan.add(new SubModel("卤豆干",3));


        List<SubModel> xiaoliao = new ArrayList<>();
        xiaoliao.add(new SubModel("大蒜两粒",0.5));
        xiaoliao.add(new SubModel("辣椒包",0.5));
        xiaoliao.add(new SubModel("醋包",0.5));


        List<SubModel> yinpin = new ArrayList<>();
        yinpin.add(new SubModel("冰峰",6));
        yinpin.add(new SubModel("加多宝",6));
        yinpin.add(new SubModel("山楂汁",6));
        yinpin.add(new SubModel("可乐",4));
        yinpin.add(new SubModel("雪碧",4));
        yinpin.add(new SubModel("威海卫啤酒",4));
        yinpin.add(new SubModel("农夫山泉",3));

        List<SubModel> xinpin = new ArrayList<>();
        xinpin.add(new SubModel("测试",5.5));

        list.add(new Model(1,"    【夹馍】\n    正宗陕味",jiamo));
        list.add(new Model(2,"    【凉皮】\n    夹馍伴侣",liangpi));
        list.add(new Model(3,"    【面食】\n    纯手工面",mianshi));
        list.add(new Model(4,"    【佐餐】\n    汤粥小吃",zuocan));
        list.add(new Model(5,"    【小料】\n    口味调节",xiaoliao));
        list.add(new Model(6,"    【饮品】\n    特色饮品",yinpin));
        list.add(new Model(7,"    【新品】\n    新品上市",xinpin));
        return list;
    }
}
