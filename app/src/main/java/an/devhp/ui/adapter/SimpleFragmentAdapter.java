package an.devhp.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import an.devhp.R;
import an.devhp.ui.fragment.SimpleFragment;
import an.devhp.ui.viewholder.SimpleFragmentViewHolder;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 10:59
 * @version: 1.0
 */

public class SimpleFragmentAdapter extends SimpleListAdapter<SimpleFragment, SimpleFragmentViewHolder> {

    public SimpleFragmentAdapter(Context context, List<SimpleFragment> data) {
        super(context, data);
    }

    @Override
    public SimpleFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleFragmentViewHolder(
                getItemView(R.layout.list_item_simple_fragment, parent, viewType));
    }

    @Override
    public void onBindViewHolder(SimpleFragmentViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        if (holder != null) {
            holder.bindData(getItem(position), getItemCount(), position);
        }
    }

}
