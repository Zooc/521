package an.devhp.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import an.devhp.ui.activity.SimpleActivity;

import static an.devhp.model.constant.IExtras.KEY_SIMPLE_FRAGMENT_ID;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-08-21 16:07
 * @version: 1.0
 */

public class AcUtil {

    private AcUtil() {
        //no instance
    }

    public static void startSimpleActivity(Context context, int simpleFragmentId, Bundle bundle) {
        Intent intent = new Intent(context, SimpleActivity.class);
        intent.putExtra(KEY_SIMPLE_FRAGMENT_ID, simpleFragmentId);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
