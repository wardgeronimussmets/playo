package wardsmetsgeronimus.playo.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import wardsmetsgeronimus.playo.R;
import wardsmetsgeronimus.playo.background.WifiScanner;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.v("Markel","goedemorgend");
        new WifiScanner(getApplicationContext(),"wifi_address");
    }
}
