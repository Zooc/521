package an.devhp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import an.devhp.Architecture.BasePresenter;
import an.devhp.R;
import an.devhp.manager.FragmentFactory;
import an.devhp.model.constant.IExtras;
import an.devhp.ui.BaseActivity;
import an.devhp.ui.fragment.SimpleFragment;
import an.devhp.util.StringUtil;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-08-21 15:58
 * @version: 1.0
 */

public class SimpleActivity extends BaseActivity {

    SimpleFragment simpleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int simpleFragmentId = intent.getIntExtra(IExtras.KEY_SIMPLE_FRAGMENT_ID, -1);
        simpleFragment = FragmentFactory.create(simpleFragmentId);

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            simpleFragment.setArguments(bundle);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_view, simpleFragment)
                .commit();
        setTitle(simpleFragment.getTitle());
    }

    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_simple, viewGroup, false);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    public void finish() {
        simpleFragment.onActivityFinish();
        super.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        simpleFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        MenuItem menuItem = menu.findItem(R.id.simple_action);
        if (menuItem != null && !StringUtil.isEmpty(simpleFragment.getSimpleActionText())) {
            menuItem.setTitle(simpleFragment.getSimpleActionText());
        } else {
            menuItem.setEnabled(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.simple_action) {
            if (simpleFragment.getSimpleActionListener() != null) {
                simpleFragment.getSimpleActionListener().onClick(null);
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
