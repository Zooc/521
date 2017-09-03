package an.devhp.util;

import an.devhp.BuildConfig;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 16:12
 * @version: 1.0
 */

public class DevUtil {
    private DevUtil() {
        //no instance
    }

    /**
     * 是否在开发模式下
     *
     * @return
     */
    public static boolean isDevMode() {
        return BuildConfig.DEV_MODE;
    }

    /**
     * 是否可debug
     *
     * @return
     */
    public static boolean debugOpen() {
        return BuildConfig.DEBUG;
    }
}
