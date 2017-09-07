package an.devhp.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import an.devhp.ui.listener.ListItemClickListener;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 14:13
 * @version: 1.0
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private View mItemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mItemView = itemView;
    }

    public View getItemView() {
        return mItemView;
    }

    public void setListItemClickListener(final ListItemClickListener l){
        if(l!=null&&mItemView!=null){
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onListItemClick(v,getLayoutPosition());
                }
            });
        }
    }
}
