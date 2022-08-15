package wardsmetsgeronimus.playo.background;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class WifiScanner {
    //two overloads, either you tell it a specific interval or it goes to the default
    //5 minutes

    private Context applicationContext;
    public WifiScanner(Context context) {
        applicationContext = context;
        start_scanning();
    }

    private void start_scanning() {
        Log.v("Markel","Trying to scan");
        PeriodicWorkRequest saveRequest = new PeriodicWorkRequest.Builder(WifiWorker.class,15,TimeUnit.MINUTES).build();
        WorkManager.getInstance(applicationContext).enqueue(saveRequest);
    }

    public static class WifiWorker extends Worker {
        public WifiWorker(
                @NonNull Context context,
                @NonNull WorkerParameters params) {
            super(context, params);
            Log.v("Markel","const");
        }

        @NonNull
        @Override
        public Result doWork() {

            // Do the work here--in this case, upload the images.
            Log.v("markel","Doing some work");

            // Indicate whether the work finished successfully with the Result
            return Result.success();
        }
    }


}