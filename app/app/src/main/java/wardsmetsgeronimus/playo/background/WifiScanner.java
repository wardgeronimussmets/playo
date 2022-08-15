package wardsmetsgeronimus.playo.background;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class WifiScanner {
    //two overloads, either you tell it a specific interval or it goes to the default
    //5 minutes
    private final static String wifi_key = "wifi";

    public WifiScanner(Context context,String targetWifi) {
        WifiWorkerStarter.init(context,targetWifi);
    }

    public static class WifiWorkerStarter{
        private static boolean initialized = false;
        static String targetWifi;
        static Context applicationContext;
        public static void init(Context applicationContext,String targetWifi){
            WifiWorkerStarter.targetWifi = targetWifi;
            WifiWorkerStarter.applicationContext = applicationContext;
            initialized = true;
            startScanner();
        }

        static void startScanner(){
            if(!initialized) {
                Log.e("WifiStarter", "Failed to initialize the Wifi starter values");
                System.exit(99990);
            }
            Log.v("Markel","Trying to scan");
            Data.Builder targetData = new Data.Builder();
            targetData.putString(wifi_key,targetWifi);

            OneTimeWorkRequest saveRequest = new OneTimeWorkRequest.Builder(WifiWorker.class)
                    .setInitialDelay(1, TimeUnit.MINUTES)
                    .setInputData(targetData.build())
                    .build();
            WorkManager.getInstance(applicationContext).enqueue(saveRequest);
        }
    }

    public static class WifiWorker extends Worker {
        private final String targetWifi;
        public WifiWorker(
                @NonNull Context context,
                @NonNull WorkerParameters params) {
            super(context, params);
            Log.v("Markel","const");
            targetWifi = params.getInputData().getString(wifi_key);
        }

        @NonNull
        @Override
        public Result doWork() {

            // Do the work here--in this case, upload the images.
            Log.v("markel","Scanning for " + targetWifi);
            WifiWorkerStarter.startScanner();
            // Indicate whether the work finished successfully with the Result
            return Result.success();
        }
    }


}