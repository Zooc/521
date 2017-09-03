package an.devhp.util;

import android.widget.TextView;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 14:17
 * @version: 1.0
 */

public class TvUtil {

    public static void setText(TextView tv, CharSequence text) {
        if (tv != null && !StringUtil.isEmpty(text)) {
            tv.setText(text);
        }
    }
}
