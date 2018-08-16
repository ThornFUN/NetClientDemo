package com.thorn.core_module.configs;

/**
 * 1. enum 修饰的枚举类
 * 2. 特点：整个程序里面保证唯一的单例，只能被初始化一次，在进行多线程操作时使用，是一种多线程安全的懒汉模式
 */

public enum ConfigType {

    /* 1. 原生开发必需的常见参数
    * */
    APPLICATION_CONTEXT,        //全局 context
    API_HOST,                   //网络请求的基本 Host
    CONFIG_READY,               //是否完成项目初始化
    ICON,                       //应用中需要使用的文字图标
    ACTIVITY,                   //若为单 Activity 的模式，则在 Activity 创建时存储一个 Activity 实例
    HANDLER,                    //不是特殊应用，单个全局 Handler 可以满足需求

    /* 2. 第三方 SDK 接口接入必需的常见参数
    * */
    WE_CHAT_APP_ID,             //微信登录、支付需要的 ID
    WE_CHAT_APP_SECRET,        //微信登录、支付需要的 SECRET

    /* 3. 混合开发需要的拦截器及常用设置的参数
    * */
    WEB_HOST,                   //混合开发时网页数据可能来自不同的 Host 地址，这里可以单独设置
    JAVASCRIPT_INTERFACE,       //设置网页 JS 与原生应用交互的关键字
    INTERCEPTORS                //对应 Web 访问的拦截器

}
