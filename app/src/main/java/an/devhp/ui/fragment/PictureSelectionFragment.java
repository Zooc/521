package an.devhp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import an.devhp.Architecture.BasePresenter;
import an.devhp.R;

/**
 * @description: 选择图片选项
 * @author: Kenny
 * @date: 2017-08-21 16:03
 * @version: 1.0
 */

public class PictureSelectionFragment extends SimpleFragment {

    public static PictureSelectionFragment newInstance() {
        return new PictureSelectionFragment();
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @NonNull
    @Override
    public String getTitle() {
        return "选择图片选型";
    }

    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture_selection, container, false);
    }
}
