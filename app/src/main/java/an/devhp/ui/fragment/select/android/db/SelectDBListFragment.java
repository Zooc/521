package an.devhp.ui.fragment.select.android.db;

import java.util.List;

import an.devhp.manager.FragmentIds;
import an.devhp.ui.fragment.SimpleFragment;
import an.devhp.ui.fragment.SimpleListFragment;

import static an.devhp.manager.FragmentFactory.create;
import static an.devhp.util.LsUtil.add;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 13:24
 * @version: 1.0
 */

public class SelectDBListFragment extends SimpleListFragment {

    public static SelectDBListFragment newInstance() {
        return new SelectDBListFragment();
    }

    @Override
    public String getTitle() {
        return "数据库选型";
    }

    @Override
    public int getSimpleFragmentId() {
        return FragmentIds.SELECT_DB;
    }

    @Override
    protected void initData(List<SimpleFragment> fragments) {
        add(fragments, create(FragmentIds.SHOW_REALM_DB));
    }

}
