package com.thorn.netclientdemo.constant;

/**
 * Created by pengj on 2018-7-26.
 * Github https://github.com/ThornFUN
 * Function:
 * 此类用于存储框架搭建期间的测试地址,数据存储在 七牛云 服务器，同时在 core 包中的 raw 文件夹中存有备份
 * BaseUrl 即为 API_HOST 定义的 http://pcclv6hgi.bkt.clouddn.com/
 */

public class UrlConstants {

    private static final String USER_PROFILE = "user_profile.json";
    private static final String INDEX_DATA_URL = "index_data.json";
    private static final String SORT_DATA_URL = "sort_list_data.json";
    private static final String SORT_CONTENT_GOODS_DATA = "sort_content_data_1.json";
    private static final String SORT_CONTENT_GOODS_DATA2 = "sort_content_data_2.json";
    private static final String SHOP_CART_DATA = "shop_cart_data.json";
    private static final String SHOP_CART_COUNT = "about.json";
    private static final String USER_ODER_LIST = "order_list.json";
    private static final String BASE_URL = "http://pcclv6hgi.bkt.clouddn.com/";

    public static String getSortDataUrl() {
        return SORT_DATA_URL;
    }

    public static String getUserProfile() {
        return USER_PROFILE;
    }

    public static String getIndexDataUrl() {
        return INDEX_DATA_URL;
    }

    public static String getSortContentGoodsData() {
        return SORT_CONTENT_GOODS_DATA;
    }

    public static String getSortContentGoodsData2() {
        return SORT_CONTENT_GOODS_DATA2;
    }

    public static String getShopCartData() {
        return SHOP_CART_DATA;
    }

    public static String getShopCartCount() {
        return SHOP_CART_COUNT;
    }

    public static String getUserOderList() {
        return USER_ODER_LIST;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
