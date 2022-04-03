package rs.raf.project1.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.project1.view.fragments.DoneFragment;
import rs.raf.project1.view.fragments.InProgressFragment;
import rs.raf.project1.view.fragments.ToDoFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private final int ITEM_COUNT = 3;
    public static final int TODO_FRAGMENT = 0;
    public static final int IN_PROGRESS_FRAGMENT = 1;
    public static final int DONE_FRAGMENT = 2;

    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case TODO_FRAGMENT: fragment = new ToDoFragment(); break;
            case IN_PROGRESS_FRAGMENT: fragment = new InProgressFragment(); break;
            default: fragment = new DoneFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case TODO_FRAGMENT: return "1";
            case IN_PROGRESS_FRAGMENT: return "2";
            default: return "3";
        }
    }
}
