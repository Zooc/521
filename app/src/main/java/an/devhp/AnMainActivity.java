package an.devhp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import an.devhp.Architecture.BasePresenter;
import an.devhp.manager.SimpleFragmentId;
import an.devhp.ui.BaseActivity;
import an.devhp.util.AcUtil;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-06-23 00:35
 * @version: 1.0
 */

public class AnMainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcUtil.startSimpleActivity(AnMainActivity.this, SimpleFragmentId.SELECT_PICTURE, null);
            }
        });
    }

    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.acty_an_main,container,false);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
