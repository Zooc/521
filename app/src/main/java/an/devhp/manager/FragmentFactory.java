package an.devhp.manager;

import an.devhp.ui.fragment.HomeFragment;
import an.devhp.ui.fragment.SimpleFragment;
import an.devhp.ui.fragment.select.android.SelectAndroidTechListFragment;
import an.devhp.ui.fragment.select.android.db.SelectDBListFragment;
import an.devhp.ui.fragment.select.android.db.ShowRealmFragment;
import an.devhp.ui.fragment.select.android.media.SelectMediaListFragment;
import an.devhp.ui.fragment.select.android.media.ShowExoPlayerFragment;
import an.devhp.ui.fragment.select.android.pic.SelectPicListFragment;

import static an.devhp.manager.FragmentIds.HOME;
import static an.devhp.manager.FragmentIds.SELECT_ANDROID_TECH;
import static an.devhp.manager.FragmentIds.SELECT_DB;
import static an.devhp.manager.FragmentIds.SELECT_MEDIA;
import static an.devhp.manager.FragmentIds.SELECT_PICTURE;
import static an.devhp.manager.FragmentIds.SHOW_EXO_PLAYER_MEDIA;
import static an.devhp.manager.FragmentIds.SHOW_REALM_DB;

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

    public static SimpleFragment create(long id) {
        if (id == HOME) {
            return HomeFragment.newInstance();
        } else if (id == SELECT_ANDROID_TECH) {
            return SelectAndroidTechListFragment.newInstance();
        } else if (id == SELECT_PICTURE) {
            return SelectPicListFragment.newInstance();
        } else if (id == SELECT_DB) {
            return SelectDBListFragment.newInstance();
        } else if (id == SHOW_REALM_DB) {
            return ShowRealmFragment.newInstance();
        } else if (id == SELECT_MEDIA) {
            return SelectMediaListFragment.newInstance();
        } else if (id == SHOW_EXO_PLAYER_MEDIA) {
            return ShowExoPlayerFragment.newInstance();
        }
        return null;
    }

}
