package an.devhp.ui.fragment;

import java.util.List;

import an.devhp.manager.FragmentFactory;
import an.devhp.manager.FragmentIds;
import an.devhp.util.DevUtil;

import static an.devhp.manager.FragmentIds.SELECT_ANDROID_TECH;
import static an.devhp.manager.FragmentIds.SHOW_REALM_DB;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 12:49
 * @version: 1.0
 */

public class HomeFragment extends SimpleListFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public String getTitle() {
        return "Android Development Helper";
    }

    @Override
    public int getSimpleFragmentId() {
        return FragmentIds.HOME;
    }

    @Override
    protected void initData(List<SimpleFragment> fragments) {
        if (DevUtil.isDevMode()) {
//            fragments.add(FragmentFactory.create(SHOW_REALM_DB));
        }
        super.initData(fragments);

    }

    @Override
    public int[] getFragmentIds() {
        return new int[]{SELECT_ANDROID_TECH};
    }
}
