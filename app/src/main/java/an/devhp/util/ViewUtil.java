package an.devhp.util;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-08-21 16:40
 * @version: 1.0
 */

public class ViewUtil {

    private ViewUtil() {
        //no instance
    }

    public static <T extends View> T findById(@NonNull View view, @IdRes int id) {
        return ButterKnife.findById(view, id);
    }

    public static <T extends View> T findById(@NonNull Activity activity, @IdRes int id) {
        return ButterKnife.findById(activity, id);
    }

    public static <T extends View> T findById(@NonNull Dialog dialog, @IdRes int id) {
        return ButterKnife.findById(dialog, id);
    }
}
