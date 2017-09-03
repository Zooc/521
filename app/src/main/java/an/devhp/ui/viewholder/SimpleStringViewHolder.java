package an.devhp.ui.viewholder;

import android.view.View;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 14:21
 * @version: 1.0
 */

public class SimpleStringViewHolder extends SimpleTextViewHolder<String> {

    public SimpleStringViewHolder(View itemView) {
        super(itemView);
    }

    public SimpleStringViewHolder(View itemView, int textGravity) {
        super(itemView, textGravity);
    }

    @Override
    protected CharSequence getSimpleItemText(String s) {
        return s;
    }
}
