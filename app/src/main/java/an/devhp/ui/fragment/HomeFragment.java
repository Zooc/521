package an.devhp.ui.fragment;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import an.devhp.manager.AppPref;
import an.devhp.manager.FragmentFactory;
import an.devhp.manager.FragmentIds;
import an.devhp.ui.adapter.SimpleFragmentAdapter;
import an.devhp.ui.adapter.SimpleListAdapter;
import an.devhp.util.LsUtil;

import static an.devhp.manager.FragmentIds.SELECT_ANDROID_TECH;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 12:49
 * @version: 1.0
 */

public class HomeFragment extends SimpleListFragment {

    //    固有列表
    private List<SimpleFragment> mOriginalList = new ArrayList<>();

    //    前面已存在的选项
    private List<Long> mOriginExistExtraList = new ArrayList<>();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public String getTitle() {
        return "Android Development Helper";
    }

    @Override
    public long getSimpleFragmentId() {
        return FragmentIds.HOME;
    }

    @Override
    protected void initData(List<SimpleFragment> fragments) {
        super.initData(fragments);
        mOriginalList.addAll(fragments);
        addExtraFragmentItems(fragments);
    }

    private void addExtraFragmentItems(List<SimpleFragment> fragments) {
        List<Long> extraList = AppPref.getInstance().getItemShowAtHome();
        if (LsUtil.isListDifferent(extraList, mOriginExistExtraList)) {
            mOriginExistExtraList.clear();
            mOriginExistExtraList.addAll(extraList);
        }
        for (Long id : extraList) {
            LsUtil.add(fragments, FragmentFactory.create(id));
        }
    }

    @Override
    public long[] getFragmentIds() {
        return new long[]{SELECT_ANDROID_TECH};
    }

    @Override
    protected void onFragmentListItemLongClicked(int position, SimpleFragmentAdapter adapter) {
        Object t = LsUtil.getLsElement(adapter.getData(), position);
        if (t instanceof SimpleFragment) {
            SimpleFragment sf = (SimpleFragment) t;
            long fragmentId = sf.getSimpleFragmentId();
            if (AppPref.getInstance().deleteItemShowAtHome(fragmentId)) {
                adapter.getData().remove(sf);
                adapter.notifyDataSetChanged();
                Toast.makeText(mActivity, "Item deleted", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getRecyclerView().getAdapter() instanceof SimpleListAdapter) {
            if (LsUtil.isListDifferent(AppPref.getInstance().getItemShowAtHome(), mOriginExistExtraList)) {
                SimpleListAdapter adapter = (SimpleListAdapter) getRecyclerView().getAdapter();
                List<SimpleFragment> data = adapter.getData();
                data.clear();
                data.addAll(mOriginalList);
                addExtraFragmentItems(data);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
