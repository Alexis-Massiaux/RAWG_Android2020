package android.eservices.rawg;

import android.eservices.rawg.data.di.FakeDependencyInjection;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.fragment.GamesCollectionFragment;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.fragment.GameSearchFragment;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.fragment.YoutubeVideosFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Main activity of the application
 */
public class RawgDisplayActivity extends AppCompatActivity {

    //Slide between fragments
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FakeDependencyInjection.setContext(this);

        setupViewPagerAndTabs();
    }

    /**
     * Fill the method to get view references and initialize viewpager to display our fragments
     */
    private void setupViewPagerAndTabs() {
        PagerAdapter pagerAdapter;

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return new YoutubeVideosFragment();
            else if(position == 1)
                return new GamesCollectionFragment();
            return new GameSearchFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0)
                return YoutubeVideosFragment.TAB_NAME;
            else if(position == 1)
                return GamesCollectionFragment.TAB_NAME;
            else
                return GameSearchFragment.TAB_NAME;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
