package an.devhp.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;

import java.util.List;

import an.devhp.R;
import an.devhp.ui.viewholder.SimpleStringViewHolder;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 14:24
 * @version: 1.0
 */

public class SimpleStringListAdapter extends SimpleListAdapter<String, SimpleStringViewHolder> {

    public SimpleStringListAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public SimpleStringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleStringViewHolder(getItemView(R.layout.list_item_simple_fragment, parent, viewType), Gravity.CENTER);
    }

    @Override
    public void onBindViewHolder(SimpleStringViewHolder holder, int position) {
        if (holder != null) {
            holder.bindData(getData(), getItem(position), getItemCount(), position, getItemClickListener());
        }
    }
}
