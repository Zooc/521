package an.devhp.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import an.devhp.ui.listener.ListItemClickListener;
import an.devhp.ui.viewholder.BaseViewHolder;
import an.devhp.util.LsUtil;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 14:01
 * @version: 1.0
 */

public abstract class SimpleListAdapter<T, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mData;

    private ListItemClickListener mItemClickListener;
    private ListItemClickListener mLongClickListener;

    public void setOnItemClickListener(ListItemClickListener l) {
        mItemClickListener = l;
    }

    public void setOnItemLongClickListener(ListItemClickListener l) {
        mLongClickListener = l;
    }

    public SimpleListAdapter(Context context, List<T> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getItem(int position) {
        return LsUtil.getLsElement(mData, position);
    }

    protected Context getContext() {
        return mContext;
    }

    protected LayoutInflater getInflater() {
        return mInflater;
    }

    protected View getItemView(@LayoutRes int layoutId, ViewGroup parent, int viewType) {
        return mInflater.inflate(mContext.getResources().getLayout(layoutId), parent, false);
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        if (holder != null) {
            holder.setListItemClickListener(mItemClickListener);
            holder.setListItemLongClickListener(mLongClickListener);
        }
    }
}
