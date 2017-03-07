package com.finedu.app.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.finedu.app.R;
import com.finedu.app.adapters.PagerAdapter;
import com.finedu.app.fragments.AboutUsFragment;
import com.finedu.app.fragments.HomeFragment;
import com.finedu.app.fragments.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        initTab();
    }

    private void initLayout()
    {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);
        mTabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFrag(new HomeFragment());
        mPagerAdapter.addFrag(new VideoFragment());
        mPagerAdapter.addFrag(new AboutUsFragment());
        viewPager.setAdapter(mPagerAdapter);
    }

    private void initTab()
    {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_videos);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_about_us);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, mTabLayout, false);

            ImageView tabTextView = (ImageView) relativeLayout.findViewById(R.id.tab_icon);
            tabTextView.setImageDrawable(tab.getIcon());
            tab.setCustomView(relativeLayout);
        }
    }
}
