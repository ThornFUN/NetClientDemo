package com.thorn.core_module.configs;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.Interceptor;


/**
 * 使用 Interceptor 需要导入 okhttp 包
 */
public final class Configurator {

    private static final HashMap<Object, Object> PROJECT_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    // 1. 构造方法中初始化 项目标志位 和 Handler
    private Configurator() {
        PROJECT_CONFIGS.put(ConfigType.CONFIG_READY, false);
        PROJECT_CONFIGS.put(ConfigType.HANDLER, HANDLER);
    }

    /*
     * 2. 静态内部类的方式返回 Configurator 实例
     * */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /*
    *  3. 返回项目初始化前的参数配置表 HashMap ，项目初始化的时候调用
    * */
    final   HashMap<Object, Object> getConfigsBeforeInit() {
        return PROJECT_CONFIGS;
    }

    /*
     * 4.返回项目初始化完成后的参数配置表，项目运行时调用
     * */
    final HashMap<Object, Object> getConfigsAfterInit() {
        checkConfigration();
        return PROJECT_CONFIGS;
    }

    /*
    * 5. 从参数配置表中获取指定 key 的 value 值，并且强制转型为对应实例
    * */
    @SuppressWarnings("unchecked")
    final <T> T getSingleValueAfterInit(Enum<ConfigType> key) {
        checkConfigration();
        final Object value = PROJECT_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + "is null,please check your key");
        } else {
            return (T) value;
        }
    }

    /*
     * 6. 修改项目初始化标志位的方法，表明项目参数配置初始化完成
     * */
    public final void configure() {
        PROJECT_CONFIGS.put(ConfigType.CONFIG_READY, true);
    }

    /*
    * 检查项目参数是否已经初始化
    */
    private void checkConfigration() {
        final boolean isReady = (boolean) PROJECT_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Config is not ready,call configure");
        }
    }


    /*
     *  -------------------------------- 后面一系列初始化参数的方法 --------------------------------------------
     */
    public final Configurator withApiHost(String host) {
        PROJECT_CONFIGS.put(ConfigType.API_HOST, host);
        return this;//将原对象添加属性后，重新赋值回去
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        PROJECT_CONFIGS.put(ConfigType.INTERCEPTORS, INTERCEPTORS);
        return this;
    }

    public final Configurator withJavascriptInterface(@NonNull String name) {
        PROJECT_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    //浏览器 WebView  加载的 Host
    public final Configurator withWebHost(String webHost) {
        PROJECT_CONFIGS.put(ConfigType.WEB_HOST, webHost);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        PROJECT_CONFIGS.put(ConfigType.INTERCEPTORS, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        PROJECT_CONFIGS.put(ConfigType.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        PROJECT_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        PROJECT_CONFIGS.put(ConfigType.ACTIVITY, activity);
        return this;
    }
}
