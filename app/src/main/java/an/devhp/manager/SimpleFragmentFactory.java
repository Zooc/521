package an.devhp.manager;

import an.devhp.ui.fragment.PictureSelectionFragment;
import an.devhp.ui.fragment.SimpleFragment;

/**
 * @description: 简单显示Fragment的工厂管理类
 * @author: Kenny
 * @date: 2017-08-21 16:01
 * @version: 1.0
 */

public class SimpleFragmentFactory {

    private SimpleFragmentFactory() {
        //no instance
    }

    public static SimpleFragment create(int fragmentId) {
        switch (fragmentId) {
            case SimpleFragmentId.SELECT_PICTURE:
                return PictureSelectionFragment.newInstance();
        }
        return null;
    }

}
