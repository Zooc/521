package an.devhp.Architecture;

import java.lang.ref.WeakReference;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-08-21 15:43
 * @version: 1.0
 */

public class BasePresenter<V extends IBaseView> implements IPresenter<V> {

    private WeakReference<V> mViewReference;

    @Override
    public void attachView(V view) {
        mViewReference = new WeakReference<V>(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        unScribeAllSubscription();
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }

    }

    @Override
    public V getView() {
        return mViewReference == null ? null : mViewReference.get();
    }

    public void unScribeAllSubscription() {

    }

    public boolean isViewAttached() {
        return getView() != null;
    }

}
