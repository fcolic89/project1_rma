package rs.raf.project1.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import rs.raf.project1.R;
import rs.raf.project1.view.viewpager.TabPagerAdapter;

public class TicketsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public TicketsFragment() {
        super(R.layout.fragment_tickets);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }


    private void init(View view){
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.vpTickets);

        viewPager.setAdapter(new TabPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("ToDo");
        tabLayout.getTabAt(1).setText("In progress");
        tabLayout.getTabAt(2).setText("Done");

    }
}