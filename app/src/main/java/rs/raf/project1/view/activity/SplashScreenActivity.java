package rs.raf.project1.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Intent intent;
    private boolean logged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            if (sharedPreferences.getString(LoginActivity.PREF_LOGGEDIN, "").equals("")) {
                startActivity(new Intent(this, LoginActivity.class));
            }
            else {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
            return false;
        });

        //setContentView(rs.raf.project1.R.layout.activity_splash_screen);
    }
}