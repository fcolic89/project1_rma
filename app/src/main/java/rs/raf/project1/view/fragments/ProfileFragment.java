package rs.raf.project1.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rs.raf.project1.R;
import rs.raf.project1.view.activity.LoginActivity;
import rs.raf.project1.view.activity.MainActivity;

public class ProfileFragment extends Fragment {

    private TextView tvUsername;
    private TextView tvEmail;
    private Button btnLogOut;
    private SharedPreferences sharedPreferences;
    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view){
        sharedPreferences = requireActivity().getSharedPreferences(requireActivity().getPackageName(), Context.MODE_PRIVATE);
        tvUsername = view.findViewById(R.id.tvProfileUsername);
        tvEmail = view.findViewById(R.id.tvProfileEmail);
        btnLogOut = view.findViewById(R.id.btnLogOut);

        tvUsername.setText(sharedPreferences.getString(LoginActivity.PREF_USERNAME, ""));
        tvEmail.setText(sharedPreferences.getString(LoginActivity.PREF_EMAIL, ""));

        btnLogOut.setOnClickListener(v -> {
            sharedPreferences.edit().putString(LoginActivity.PREF_LOGGEDIN, "").apply();
            sharedPreferences.edit().putString(LoginActivity.PREF_USERNAME, "").apply();
            sharedPreferences.edit().putString(LoginActivity.PREF_EMAIL, "").apply();
            startActivity(new Intent(requireActivity(), LoginActivity.class));
            requireActivity().finish();
        });
    }
}