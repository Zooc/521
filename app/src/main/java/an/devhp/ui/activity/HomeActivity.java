package an.devhp.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import an.devhp.Architecture.BasePresenter;
import an.devhp.R;
import an.devhp.manager.FragmentFactory;
import an.devhp.manager.FragmentIds;
import an.devhp.ui.BaseActivity;
import androidx.annotation.Nullable;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-06-23 00:35
 * @version: 1.0
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_view,
                        FragmentFactory.create(FragmentIds.HOME))
                .commitAllowingStateLoss();
    }

    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.acty_an_main, container, false);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
