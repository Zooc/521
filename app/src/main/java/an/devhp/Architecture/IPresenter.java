package an.devhp.Architecture;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-08-21 15:42
 * @version: 1.0
 */

interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView(boolean retainInstance);

    V getView();
}
