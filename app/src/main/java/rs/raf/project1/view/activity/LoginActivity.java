package rs.raf.project1.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.splashscreen.SplashScreen;

import rs.raf.project1.R;
import rs.raf.project1.viewmodel.SplashViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;
    private SplashViewModel splashViewModel;

    public static final String PREF_PASSWORD_ADMIN = "admin";
    public static final String PREF_PASSWORD_REGULAR = "password";
    public static final String PREF_EMAIL = "user_email";
    public static final String PREF_LOGGEDIN = "isLoggedIn";
    public static final String PREF_USERNAME = "user_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUsers();
//        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
//        splashScreen.setKeepOnScreenCondition(() -> {
//            if (!sharedPreferences.getString(PREF_LOGGEDIN, "").equals("")) {
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                //finish();
//            }
//            return false;
//        });
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initView();
        initListeners();
    }

    private void initUsers() {
        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(PREF_PASSWORD_ADMIN, "admin").apply();
        sharedPreferences.edit().putString(PREF_PASSWORD_REGULAR, "regular").apply();
        //sharedPreferences.edit().putString(PREF_LOGGEDIN, "").apply();
    }

    private void initView() {
        this.etUsername = findViewById(R.id.etUsername);
        this.etEmail = findViewById(R.id.etEmail);
        this.etPassword = findViewById(R.id.etPassword);
        this.btnLogin = findViewById(R.id.btnLogin);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(v -> {
            Toast t;
            if (etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")
                    || etEmail.getText().toString().equals("")) {
                t = Toast.makeText(getApplicationContext(), "All fields must be filled!", Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            } else if (etUsername.getText().toString().startsWith("admin") && etPassword.getText().toString().equals(sharedPreferences.getString(PREF_PASSWORD_ADMIN, ""))) {
                sharedPreferences.edit().putString(PREF_LOGGEDIN, "ADMIN").apply();
                setAndContinue();
            } else if (!etUsername.getText().toString().startsWith("admin") && etPassword.getText().toString().equals(sharedPreferences.getString(PREF_PASSWORD_REGULAR, ""))) {
                sharedPreferences.edit().putString(PREF_LOGGEDIN, "REGULAR").apply();
                setAndContinue();
            } else {
                t = Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
        });
    }

    private void setAndContinue() {
        sharedPreferences.edit().putString(PREF_USERNAME, etUsername.getText().toString()).apply();
        sharedPreferences.edit().putString(PREF_EMAIL, etEmail.getText().toString()).apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}