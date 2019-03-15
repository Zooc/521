package an.devhp.ui.fragment.select.android.db;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import an.devhp.R;
import an.devhp.manager.FragmentIds;
import an.devhp.model.entity.realm.User;
import an.devhp.ui.adapter.SimpleStringListAdapter;
import an.devhp.ui.fragment.SimpleListFragment;
import an.devhp.ui.listener.ListItemClickListener;
import an.devhp.util.LsUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 13:37
 * @version: 1.0
 */

public class ShowRealmFragment extends SimpleListFragment {

    @BindView(R.id.show_list_rv)
    RecyclerView mRvShowList;

    private SimpleStringListAdapter mShowListAdapter;
    private List<String> mShowList = new ArrayList<>();

    HashMap<String, Integer> map = new HashMap<>();

    public static ShowRealmFragment newInstance() {
        return new ShowRealmFragment();
    }

    @Override
    protected boolean addFragmentList() {
        return false;
    }

    @Override
    public String getTitle() {
        return "Realm Show";
    }

    @Override
    public long getSimpleFragmentId() {
        return FragmentIds.SHOW_REALM_DB;
    }

    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_db, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRvShowList.setLayoutManager(new LinearLayoutManager(mActivity));

        List<String> list = new ArrayList<>();
        LsUtil.add(list,
                "增",
                "删全部", "删第一个", "删除第5个", "删位置为1的数据", "删最后一个",
                "改最后一个", "改全部",
                "查询全部", "查询降序", "查询升序",
                "异步操作");
        final SimpleStringListAdapter adapter = new SimpleStringListAdapter(mActivity, list);
        adapter.setOnItemClickListener(new ListItemClickListener() {
            @Override
            public void onListItemClick(View view, int position) {
                Object t = LsUtil.getLsElement(adapter.getData(),position);
                if (t instanceof String) {
                    String key = (String) t;
                    final Realm realm = Realm.getDefaultInstance();
                    if ("增".equals(key)) {

                        realm.beginTransaction();
                        User user = realm.createObject(User.class);
                        Integer addCount = map.get(key);
                        if (addCount == null) {
                            addCount = 0;
                        }
                        user.name = key + " " + addCount;
                        map.put(key, ++addCount);
                        realm.commitTransaction();

                    } else if (key.startsWith("删")) {
                        final RealmResults<User> result = realm.where(User.class).findAll();
                        if ("删全部".equals(key)) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    result.deleteAllFromRealm();
                                }
                            });
                        } else if ("删第一个".equals(key)) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    result.deleteFirstFromRealm();
                                }
                            });
                        } else if ("删除第5个".equals(key)) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    User user = LsUtil.getLsElement(result, 5);
                                    if (user != null) {
                                        user.deleteFromRealm();
                                    }
                                }
                            });
                        } else if ("删位置为1的数据".equals(key)) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    if(result.size()>1){
                                        result.deleteFromRealm(1);
                                    }
                                }
                            });
                        } else if ("删最后一个".equals(key)) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    result.deleteLastFromRealm();
                                }
                            });
                        }
                    } else if ("改最后一个".equals(key)) {
                        RealmResults<User> result = realm.where(User.class).findAll();
                        if(result.size()>0){
                            User user = result.get(result.size() - 1);
                            realm.beginTransaction();
                            user.name = "改了最后一个";
                            realm.commitTransaction();
                        }
                    } else if ("改全部".equals(key)) {
                        RealmResults<User> result = realm.where(User.class).findAll();
                        realm.beginTransaction();
                        for (User u :
                                result) {
                            u.name += "改全部";
                        }
                        realm.commitTransaction();
                    } else if (key.startsWith("查询")) {
                        RealmResults<User> result = realm.where(User.class).findAll();
                        if ("查询降序".equals(key)) {
                            result = result.sort("name", Sort.DESCENDING);
                        } else if ("查询升序".equals(key)) {
                            result = result.sort("name", Sort.ASCENDING);
                        } else if ("查询全部".equals(key)) {
                            queryAll(realm);
                            return;
                        }
                        List<User> userNewList = realm.copyFromRealm(result);
                        if (userNewList != null) {
                            mShowList.clear();
                            for (User u : userNewList) {
                                mShowList.add(u.name);
                            }
                            setShowListView();
                        }
                        return;
                    } else if ("异步操作".equals(key)) {
                        realm.executeTransactionAsync(new Realm.Transaction() {

                            @Override
                            public void execute(Realm realm) {
                                User user = realm.createObject(User.class);
                                user.name = "异步创建的对象";
                                realm.copyToRealm(user);
                            }
                        }, new Realm.Transaction.OnSuccess() {

                            @Override
                            public void onSuccess() {
                                Toast.makeText(mActivity, "异步操作成功了", Toast.LENGTH_LONG).show();
                                queryAll(realm);
                            }
                        }, new Realm.Transaction.OnError() {

                            @Override
                            public void onError(Throwable error) {
                                Toast.makeText(mActivity, "异步操作失败了", Toast.LENGTH_LONG).show();
                            }
                        });
                        return;
                    }
                    queryAll(realm);
                }

            }
        });
        setAdapter(adapter);

    }

    private void queryAll(Realm realm) {
        RealmResults<User> users = realm.where(User.class).findAll();
        List<User> userNewList = realm.copyFromRealm(users);
        if (userNewList != null) {
            mShowList.clear();
            for (User u : userNewList) {
                mShowList.add(u.name);
            }
            setShowListView();
        }
    }

    private void setShowListView() {
        if (mShowListAdapter == null) {
            mShowListAdapter = new SimpleStringListAdapter(mActivity, mShowList);
            mRvShowList.setAdapter(mShowListAdapter);
        } else {
            mShowListAdapter.notifyDataSetChanged();
        }

    }
}
