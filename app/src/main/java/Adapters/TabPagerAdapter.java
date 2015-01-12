package Adapters;

/**
 * Created by daniellin on 14/12/27.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import FragmentPages.*;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = { "New Arrivals & Discounts", "About T-Dan Bubble Tea", "Contact Information"};

    public TabPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {return TITLES[position];}

    @Override
    public int getCount() {return TITLES.length;}

    @Override
    public Fragment getItem(int index){
        switch (index) {
            case 0:
                // Tea Fragment
                return new TeaFragment();
            case 1:
                // AboutTeaDan fragment
                return new AboutTeaDan();
            case 2:
                // Contact Information Fragment
                return new ContactInformation();
        }

        return null;
    }



    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
