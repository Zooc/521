package an.devhp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import an.devhp.Architecture.BasePresenter;
import an.devhp.Architecture.IBaseView;
import an.devhp.R;
import an.devhp.common.CommonActivity;
import an.devhp.util.ViewUtil;
import butterknife.ButterKnife;

import static an.devhp.util.ViewUtil.findById;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-06-23 00:40
 * @version: 1.0
 */

public abstract class BaseActivity extends CommonActivity implements IBaseView {

    protected boolean mIsRecreated;
    private ViewGroup mRootView;
    private LinearLayout mTitleContainer;
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private BasePresenter mBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActivityCreate(savedInstanceState);
        mIsRecreated = (savedInstanceState != null);
        createViewFrame(savedInstanceState);
        ButterKnife.bind(this, mRootView);
        initPresenter();
        if (mBasePresenter != null) {
            mBasePresenter.attachView(this);
        }
    }

    private void initPresenter() {
        if (mBasePresenter == null) {
            mBasePresenter = onCreatePresenter();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.detachView(false);
            mBasePresenter = null;
        }
    }

    /**
     * Be called before setContentView
     *
     * @param savedInstanceState
     */
    protected void onActivityCreate(Bundle savedInstanceState) {
    }

    /**
     * BaseActivity view frame
     *
     * @param savedInstanceState
     */
    private void createViewFrame(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(this);
        mRootView = onCreateRootView(inflater, savedInstanceState);
        mTitleContainer = findById(mRootView, R.id.header_title);

        //1.Title
        View titleView = onCreateTitleView(inflater, mTitleContainer, savedInstanceState);
        if (titleView != null && mTitleContainer != null) {
            if (mTitleContainer.getChildCount() != 0) {
                mTitleContainer.removeAllViews();
            }
            addTitleView(titleView);
        }

        //2.Content
        View contentView = onCreateContentView(inflater, mRootView, savedInstanceState);
        if (contentView != null) {
            addContentView(contentView);
        }

        if (mTitleContainer == null) {
            mToolbar = findById(mRootView, R.id.tool_bar);
        } else {
            mToolbar = findById(mTitleContainer, R.id.tool_bar);
        }

        if (mToolbar != null) {
            mTvTitle = findById(mToolbar, R.id.toolbar_title);
            setSupportActionBar(mToolbar);
            initToolbar(mToolbar);
        }

        if (mRootView.getParent() == null) {
            setContentView(mRootView);
        }
    }

    protected void addTitleView(View titleView) {
        mTitleContainer.addView(titleView);
    }

    protected void addContentView(View contentView) {
        contentView.setId(R.id.content_view);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mRootView.addView(contentView, params);
    }

    protected void initToolbar(Toolbar toolbar) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }

    protected ViewGroup getRootView() {
        return mRootView;
    }

    protected LinearLayout getTitleContainer() {
        return mTitleContainer;
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    protected ViewGroup onCreateRootView(LayoutInflater inflater, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.activity_frame, null);
    }

    /**
     * Use toolbar as default titlebar
     */
    protected View onCreateTitleView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    protected BasePresenter getPresenter() {
        return mBasePresenter;
    }

    protected abstract View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract BasePresenter onCreatePresenter();

}
