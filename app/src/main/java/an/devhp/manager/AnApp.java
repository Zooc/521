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

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration
                = new RealmConfiguration.Builder().name("andevhelper.realm").build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
