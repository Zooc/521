package an.devhp.ui.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import an.devhp.R;
import an.devhp.ui.listener.ListItemClickListener;
import an.devhp.util.TvUtil;
import butterknife.BindView;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 14:13
 * @version: 1.0
 */

public abstract class SimpleTextViewHolder<T> extends BaseViewHolder {

    @BindView(R.id.item_tv)
    TextView tvItem;
    @BindView(R.id.line_v)
    View vLine;

    public SimpleTextViewHolder(View itemView) {
        super(itemView);
    }

    public SimpleTextViewHolder(View itemView, int textGravity) {
        super(itemView);
        tvItem.setGravity(textGravity);
    }

    public void bindData(T t, int itemCount, final int position) {
        if (t != null) {
            TvUtil.setText(tvItem, getSimpleItemText(t));
        }

        if (position == itemCount - 1) {
            vLine.setVisibility(View.GONE);
        } else {
            vLine.setVisibility(View.VISIBLE);
        }
    }

    protected abstract CharSequence getSimpleItemText(T t);

}
