package an.devhp.ui.fragment.select.android;

import java.util.List;

import an.devhp.manager.FragmentIds;
import an.devhp.ui.fragment.SimpleFragment;
import an.devhp.ui.fragment.SimpleListFragment;

import static an.devhp.manager.FragmentFactory.create;
import static an.devhp.manager.FragmentIds.SELECT_DB;
import static an.devhp.manager.FragmentIds.SELECT_MEDIA;
import static an.devhp.manager.FragmentIds.SELECT_PICTURE;
import static an.devhp.util.LsUtil.add;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 10:57
 * @version: 1.0
 */

public class SelectAndroidTechListFragment extends SimpleListFragment {

    @Override
    public String getTitle() {
        return "安卓技术选型";
    }

    @Override
    public int getSimpleFragmentId() {
        return FragmentIds.SELECT_ANDROID_TECH;
    }

    public static SelectAndroidTechListFragment newInstance() {
        return new SelectAndroidTechListFragment();
    }

    @Override
    public int[] getFragmentIds() {
        return new int[]{SELECT_PICTURE,SELECT_DB,SELECT_MEDIA};
    }
}
