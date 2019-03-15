package an.devhp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Field;
import java.util.List;

import an.devhp.Architecture.BasePresenter;
import an.devhp.Architecture.IBaseView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;

/**
 * @description: Fragment基类
 * @author: Kenny
 * @date: 2017-08-21 15:40
 * @version: 1.0
 */

public abstract class BaseFragment extends Fragment implements IBaseView {

    protected BaseActivity mActivity;//    宿主Activity
    protected boolean mIsVisibleToUser;//    是否可见
    protected boolean mIsCreated;//    activity是否已创建
    private BasePresenter mPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            ButterKnife.bind(this, view);
        }

        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof BaseActivity) {
            mActivity = (BaseActivity) getActivity();
        } else {
            throw new IllegalStateException("BaseFragment is not in BaseActivity!");
        }

        mIsCreated = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView(getRetainInstance());
        }
    }

    private void initPresenter() {
        if (mPresenter == null) {
            mPresenter = onCreatePresenter();
        }
    }

    public boolean isVisibleToUser() {
        return mIsVisibleToUser;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            onUserVisible();
        } else {
            onUserInvisible();
        }
    }

    /**
     * 对用户可见
     */
    public void onUserVisible() {
    }

    /**
     * 对用户不可见
     */
    public void onUserInvisible() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //修复No Host 或者 No Activity的问题
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    public void onActivityEvent(int eventType, Object object) {
        FragmentManager fragmentManager = getChildFragmentManager();
        if (fragmentManager != null) {
            List<Fragment> childrenFragment = fragmentManager.getFragments();
            if (childrenFragment != null && !childrenFragment.isEmpty()) {
                for (Fragment fragment : childrenFragment) {
                    if (fragment instanceof BaseFragment) {
                        ((BaseFragment) fragment).onActivityEvent(eventType, object);
                    }
                }
            }
        }
    }

    protected boolean isShown() {
        BaseFragment current = this;
        do {
            if (!current.isVisibleToUser()) {
                return false;
            }
            Fragment parent = current.getParentFragment();
            current = (BaseFragment) parent;
        } while (current != null);
        return true;
    }

    protected abstract BasePresenter onCreatePresenter();
}
