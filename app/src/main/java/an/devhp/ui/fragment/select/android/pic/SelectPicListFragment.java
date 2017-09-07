package an.devhp.ui.fragment.select.android.pic;

import android.support.annotation.NonNull;

import java.util.List;

import an.devhp.manager.FragmentIds;
import an.devhp.ui.fragment.SimpleFragment;
import an.devhp.ui.fragment.SimpleListFragment;

/**
 * @description: 选择图片选项
 * @author: Kenny
 * @date: 2017-08-21 16:03
 * @version: 1.0
 */

public class SelectPicListFragment extends SimpleListFragment {

    public static SelectPicListFragment newInstance() {
        return new SelectPicListFragment();
    }

    @NonNull
    @Override
    public String getTitle() {
        return "选择图片选型";
    }

    @Override
    public long getSimpleFragmentId() {
        return FragmentIds.SELECT_PICTURE;
    }

    @Override
    protected void initData(List<SimpleFragment> fragments) {

    }
}
