package an.devhp.ui.fragment.select.android.media;

import an.devhp.ui.fragment.SimpleListFragment;

import static an.devhp.manager.FragmentIds.SELECT_MEDIA;
import static an.devhp.manager.FragmentIds.SHOW_EXO_PLAYER_MEDIA;

/**
 * @description: 流媒体选型
 * @author: Kenny
 * @date: 2017-09-04 14:01
 * @version: 1.0
 */

public class SelectMediaListFragment extends SimpleListFragment {

    public static SelectMediaListFragment newInstance(){
        return new SelectMediaListFragment();
    }

    @Override
    public String getTitle() {
        return "流媒体选型";
    }

    @Override
    public long getSimpleFragmentId() {
        return SELECT_MEDIA;
    }

    @Override
    public long[] getFragmentIds() {
        return new long[]{SHOW_EXO_PLAYER_MEDIA};
    }
}
