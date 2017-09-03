package an.devhp.ui.viewholder;

import android.view.View;

import an.devhp.ui.fragment.SimpleFragment;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 11:05
 * @version: 1.0
 */

public class SimpleFragmentViewHolder extends SimpleTextViewHolder<SimpleFragment> {

    public SimpleFragmentViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected CharSequence getSimpleItemText(SimpleFragment fragment) {
        return fragment == null ? "" : fragment.getTitle();
    }


}
