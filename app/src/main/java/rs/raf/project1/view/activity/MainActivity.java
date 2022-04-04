package rs.raf.project1.view.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketViewModel;
import rs.raf.project1.view.viewpager.BottomNavigationPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;
    private TicketViewModel ticketViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        init();
    }

    private void init() {
        initView();
        initViewPager();
        initNavigation();
    }

    private void initViewPager() {
        viewPager.setAdapter(new BottomNavigationPagerAdapter(getSupportFragmentManager()));
    }

    private void initNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_1:
                    viewPager.setCurrentItem(BottomNavigationPagerAdapter.STATISTICS_FRAGMENT, false);
                    break;
                case R.id.navigation_2:
                    viewPager.setCurrentItem(BottomNavigationPagerAdapter.NEW_TICKET_FRAGMENT, false);
                    break;
                case R.id.navigation_3:
                    viewPager.setCurrentItem(BottomNavigationPagerAdapter.TICKETS_FRAGMENT, false);
                    break;
                case R.id.navigation_4:
                    viewPager.setCurrentItem(BottomNavigationPagerAdapter.PROFILE_FRAGMENT, false);
                    break;

            }
            return true;
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.vpMain);
        bottomNavigation = findViewById(R.id.buttomNavigation);
    }

}
