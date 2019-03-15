package an.devhp.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
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
        return view.findViewById(id);
    }

    public static <T extends View> T findById(@NonNull Activity activity, @IdRes int id) {
        return activity.findViewById(id);
    }

    public static <T extends View> T findById(@NonNull Dialog dialog, @IdRes int id) {
        return dialog.findViewById(id);
    }
}
