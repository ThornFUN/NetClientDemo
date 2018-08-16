package com.thorn.core_module.configs;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by pengj on 2018-7-13.
 * Github https://github.com/ThornFUN
 * Function:
 */

public final class Apple {

    /*
     * 1. 初始化参数配置的入口方法,获取全局 Context 并存储
     * */
    public static Configurator init(Context context) {
        getConfiguratorInstance().getConfigsBeforeInit().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return getConfiguratorInstance();
    }

    /*
    * 2. 返回配置参数列表，一个 HashMap 实例
    * */
    public static HashMap<Object, Object> getAllConfigs() {
        return getConfiguratorInstance().getConfigsAfterInit();
    }

    /*
    * 3. 根据传入的 Key ，返回对应 value 的对象
    * */
    public static <T> T getConfigByKey(Enum<ConfigType> key) {
        return getConfiguratorInstance().getSingleValueAfterInit(key);
    }

    /*
    * 4. 返回 Configurator 实例
    * */
    private static Configurator getConfiguratorInstance() {
        return Configurator.getInstance();
    }

    /*
    * 5. 返回项目的 Context
    * */
    public static Context getApplicationContext() {
        return (Context) getConfigByKey(ConfigType.APPLICATION_CONTEXT);
    }

    /*
    * 6. 返回静态全局的 Handler 实例
    * */
    public static Handler getHandler() {
        return (Handler) getConfigByKey(ConfigType.HANDLER);
    }
}
