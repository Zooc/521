package an.devhp.manager;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-06-23 00:35
 * @version: 1.0
 */

public class AnApp extends Application {

    private static AnApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(this);
        RealmConfiguration realmConfiguration
                = new RealmConfiguration.Builder().name("andevhelper.realm").build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static AnApp getInstance() {
        return mInstance;
    }
}
