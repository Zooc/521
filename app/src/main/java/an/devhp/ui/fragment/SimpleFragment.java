package an.devhp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import an.devhp.ui.BaseFragment;
import butterknife.ButterKnife;

/**
 * @description: 简单显示的Fragment抽象类
 * @author: Kenny
 * @date: 2017-08-21 15:55
 * @version: 1.0
 */

public abstract class SimpleFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateContentView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }


    public void onActivityFinish() {

    }

    public String getSimpleActionText() {
        return "";
    }

    public View.OnClickListener getSimpleActionListener() {
        return null;
    }

    /**
     * 创建内容视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 获取标题
     *
     * @return
     */
    public abstract String getTitle();


}
