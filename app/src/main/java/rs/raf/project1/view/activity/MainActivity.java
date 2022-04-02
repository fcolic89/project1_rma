package rs.raf.project1.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.project1.R;
import rs.raf.project1.view.viewpager.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initView();
        initViewPager();
        initNavigation();
    }

    private void initViewPager(){
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }

    private void initNavigation(){
        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_1: viewPager.setCurrentItem(PagerAdapter.STATISTICS_FRAGMENT, false); break;
                case R.id.navigation_2: viewPager.setCurrentItem(PagerAdapter.NEW_TICKET_FRAGMENT, false); break;
                case R.id.navigation_3: viewPager.setCurrentItem(PagerAdapter.TICKETS_FRAGMENT, false); break;
                case R.id.navigation_4: viewPager.setCurrentItem(PagerAdapter.PROFILE_FRAGMENT, false); break;

            }
            return true;
        });
    }

    private void initView(){
        viewPager = findViewById(R.id.vpMain);
        bottomNavigation = findViewById(R.id.buttomNavigation);
    }
}