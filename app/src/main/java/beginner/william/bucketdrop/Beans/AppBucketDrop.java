package beginner.william.bucketdrop.Beans;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import Adapters.Filter;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by william on 2/24/16.
 */
public class AppBucketDrop extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration configuration= new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
    }



    public static void save(Context context, int filterOption) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("filter", filterOption);
        editor.apply();
    }

    public static int load(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int filterOption = pref.getInt("filter", Filter.None);
        return filterOption;
    }
}