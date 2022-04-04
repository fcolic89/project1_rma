package rs.raf.project1.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            Intent main = new Intent(this, MainActivity.class);
            Intent login = new Intent(this, LoginActivity.class);
            if (!sharedPreferences.getString(LoginActivity.PREF_LOGGEDIN, "").equals("")) {
                System.out.println("Ovde1");
                startActivity(main);
            }
            else {
                System.out.println("Ovde2");
                startActivity(login);
            }
            return false;
        });
        //finish();
        //setContentView(rs.raf.project1.R.layout.activity_splash_screen);
    }
}