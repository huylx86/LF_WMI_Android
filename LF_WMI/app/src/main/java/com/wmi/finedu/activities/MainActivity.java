package com.wmi.finedu.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wmi.finedu.R;
import com.wmi.finedu.adapters.PagerAdapter;
import com.wmi.finedu.fragments.AboutUsFragment;
import com.wmi.finedu.fragments.HomeFragment;
import com.wmi.finedu.fragments.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private ImageButton mIbContactUs, mIbFacebook;
    private View mContactUsDetail, mFacebookDetail;

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

        mIbContactUs = (ImageButton)findViewById(R.id.ib_contact_us);
        mIbFacebook = (ImageButton)findViewById(R.id.ib_facebook);
        mContactUsDetail = findViewById(R.id.ln_contact_us_detail);
        mFacebookDetail = findViewById(R.id.ln_facebook_detail);

        final Animation showAnim = AnimationUtils.loadAnimation(this, R.anim.right_to_left_anim);
        final Animation hideAnim = AnimationUtils.loadAnimation(this, R.anim.left_to_right_anim);

        mIbContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContactUsDetail.setVisibility(View.VISIBLE);
                mContactUsDetail.startAnimation(showAnim);
            }
        });

        mIbFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFacebookDetail.setVisibility(View.VISIBLE);
                mFacebookDetail.startAnimation(showAnim);
            }
        });

        mFacebookDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFacebookDetail.startAnimation(hideAnim);
                mFacebookDetail.setVisibility(View.INVISIBLE);
            }
        });

        mContactUsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContactUsDetail.startAnimation(hideAnim);
                mContactUsDetail.setVisibility(View.INVISIBLE);
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

    private void loadbrowser(String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
