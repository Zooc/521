package an.devhp.manager;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import an.devhp.util.LsUtil;

import static an.devhp.manager.PrefKeys.ITEM_SHOWED_AT_HOME;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-07 16:22
 * @version: 1.0
 */

public class AppPref extends PreferenceHelper {

    protected final static String PREFERENCES_NAME = "android_development_helper";

    private static class HolderClass {
        private final static AppPref instance = new AppPref(AnApp.getInstance(), PREFERENCES_NAME);
    }

    private AppPref(Context context, String prefName) {
        super(context, prefName);
    }

    public static AppPref getInstance() {
        return HolderClass.instance;
    }

    /**
     * 将FragmentItem选项添加到首页显示
     *
     * @param fragmentId
     */
    public boolean putItemShowAtHome(Long fragmentId) {
        List<Long> results = getItemShowAtHome();
        if (!results.contains(fragmentId)) {
            LsUtil.add(results, fragmentId);
            putString(ITEM_SHOWED_AT_HOME, new Gson().toJson(results));
            return true;
        } else {
            return false;
        }
    }

    /**
     * 移除Fragment item id
     *
     * @param fragmentId
     * @return
     */
    public boolean deleteItemShowAtHome(long fragmentId) {
        List<Long> results = getItemShowAtHome();
        if (results.contains(fragmentId)) {
            results.remove(fragmentId);
            putString(ITEM_SHOWED_AT_HOME, new Gson().toJson(results));
            return true;
        } else {
            return false;
        }
    }

    public List<Long> getItemShowAtHome() {
        List<Double> list = new Gson().fromJson(getString(ITEM_SHOWED_AT_HOME, "[]"), List.class);
        List<Long> results = new ArrayList<>();
        for (Double d : list) {
            results.add(d.longValue());
        }
        return results;

    }


}
