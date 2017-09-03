package an.devhp.manager;

import an.devhp.ui.fragment.select.android.SelectAndroidTechListFragment;
import an.devhp.ui.fragment.HomeFragment;
import an.devhp.ui.fragment.select.android.db.ShowRealmFragment;
import an.devhp.ui.fragment.select.android.db.SelectDBListFragment;
import an.devhp.ui.fragment.select.android.pic.SelectPicListFragment;
import an.devhp.ui.fragment.SimpleFragment;

/**
 * @description: 简单显示Fragment的工厂管理类
 * @author: Kenny
 * @date: 2017-08-21 16:01
 * @version: 1.0
 */

public class FragmentFactory {

    private FragmentFactory() {
        //no instance
    }

    public static SimpleFragment create(int fragmentId) {
        switch (fragmentId) {
            case FragmentIds.HOME:
                return HomeFragment.newInstance();
            case FragmentIds.SELECT_ANDROID_TECH:
                return SelectAndroidTechListFragment.newInstance();
            case FragmentIds.SELECT_PICTURE:
                return SelectPicListFragment.newInstance();
            case FragmentIds.SELECT_DB:
                return SelectDBListFragment.newInstance();
            case FragmentIds.SHOW_REALM_DB:
                return ShowRealmFragment.newInstance();
        }
        return null;
    }

}
