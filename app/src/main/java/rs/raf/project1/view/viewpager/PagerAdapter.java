package rs.raf.project1.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.project1.view.fragments.NewTicketFragment;
import rs.raf.project1.view.fragments.ProfileFragment;
import rs.raf.project1.view.fragments.StatisticsFragment;
import rs.raf.project1.view.fragments.TicketsFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 4;
    public static final int STATISTICS_FRAGMENT = 0;
    public static final int NEW_TICKET_FRAGMENT = 1;
    public static final int TICKETS_FRAGMENT = 2;
    public static final int PROFILE_FRAGMENT = 3;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case STATISTICS_FRAGMENT: fragment = new StatisticsFragment(); break;
            case NEW_TICKET_FRAGMENT: fragment = new NewTicketFragment(); break;
            case TICKETS_FRAGMENT: fragment = new TicketsFragment(); break;
            default: fragment = new ProfileFragment(); break;
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
            case STATISTICS_FRAGMENT: return "1";
            case NEW_TICKET_FRAGMENT: return "2";
            case PROFILE_FRAGMENT: return "3";
            default: return "4";
        }
    }
}
