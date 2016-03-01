package services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;

import beginner.william.bucketdrop.Beans.Drop;
import beginner.william.bucketdrop.MainActivity;
import beginner.william.bucketdrop.R;
import br.com.goncalves.pugnotification.notification.PugNotification;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotificationService extends IntentService {

    public NotificationService() {
        super("NotificationService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Realm realm=null;

        try{
            realm=Realm.getDefaultInstance();
            RealmResults<Drop>results= realm.where(Drop.class).equalTo("completed", false).findAll() ;
            fireNotification();
            for (Drop current: results){
                if (isNotificationNeeded(current.getAdded(),current.getWhen()));

            }

        }
        finally {
            if (realm!=null){
                realm.close();
            }
        }

    }

    private void fireNotification() {
        String text="Thank you for reviewing the app";
        PugNotification.with(this)
                .load()
                .title("Dear Employer")
                .message(text)
                .smallIcon(R.drawable.ic_drop)
                .largeIcon(R.drawable.ic_drop)
                .flags(Notification.FLAG_ONLY_ALERT_ONCE)
                .autoCancel(true)
                .click(MainActivity.class)
                .simple()
                .build();
    }


    private boolean isNotificationNeeded(long added, long when){

        long now= System.currentTimeMillis();

        if (now> when){
            return false;
        }
        else{
            long difference90= (long) (0.9*(when-added));
            return (now>(added+difference90))? true:false;
        }

    }

}
