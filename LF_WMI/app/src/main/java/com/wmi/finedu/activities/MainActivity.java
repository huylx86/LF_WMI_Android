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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wmi.finedu.R;
import com.wmi.finedu.adapters.PagerAdapter;
import com.wmi.finedu.fragments.AboutUsFragment;
import com.wmi.finedu.fragments.HomeFragment;
import com.wmi.finedu.fragments.VideoFragment;
import com.wmi.finedu.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private View mMainContactUs, mMainFacebook, mMainPhone;
    private View mContactUs, mFacebook, mPhone;
    private View mContactUsDetail, mFacebookDetail, mPhoneDetail;
    private ImageView mIvContactUsDetail, mIvFacebookDetail, mIvPhoneDetail;
    private TextView mTvContactUsDetail, mTvFacebookDetail, mTvPhoneDetail;

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
                if(position == 1){
                    mMainContactUs.setVisibility(View.INVISIBLE);
                    mMainFacebook.setVisibility(View.INVISIBLE);
                    mMainPhone.setVisibility(View.INVISIBLE);
                } else {
                    mMainContactUs.setVisibility(View.VISIBLE);
                    mMainFacebook.setVisibility(View.VISIBLE);
                    mMainPhone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mMainContactUs = findViewById(R.id.rl_contact_us);
        mMainFacebook = findViewById(R.id.rl_facebook);
        mMainPhone = findViewById(R.id.rl_phone);
        mContactUs = findViewById(R.id.ln_contact_us);
        mFacebook = findViewById(R.id.ln_facebook);
        mPhone = findViewById(R.id.ln_phone);
        mContactUsDetail = findViewById(R.id.ln_contact_us_detail);
        mFacebookDetail = findViewById(R.id.ln_facebook_detail);
        mPhoneDetail = findViewById(R.id.ln_phone_detail);
        mIvContactUsDetail = (ImageView)findViewById(R.id.iv_contact_us_detail);
        mIvFacebookDetail = (ImageView)findViewById(R.id.iv_facebook_detail);
        mIvPhoneDetail = (ImageView)findViewById(R.id.iv_phone_detail);
        mTvContactUsDetail = (TextView) findViewById(R.id.tv_contact_us_detail);
        mTvFacebookDetail = (TextView)findViewById(R.id.tv_facebook_detail);
        mTvPhoneDetail = (TextView)findViewById(R.id.tv_phone_detail);

        final Animation showAnim = AnimationUtils.loadAnimation(this, R.anim.right_to_left_anim);
        final Animation hideAnim = AnimationUtils.loadAnimation(this, R.anim.left_to_right_anim);

        mContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContactUsDetail.setVisibility(View.VISIBLE);
                mContactUsDetail.startAnimation(showAnim);
                mContactUs.setVisibility(View.INVISIBLE);
            }
        });

        mFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFacebookDetail.setVisibility(View.VISIBLE);
                mFacebookDetail.startAnimation(showAnim);
                mFacebook.setVisibility(View.INVISIBLE);
            }
        });

        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneDetail.setVisibility(View.VISIBLE);
                mPhoneDetail.startAnimation(showAnim);
                mPhone.setVisibility(View.INVISIBLE);
            }
        });
        mIvContactUsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContactUs.setVisibility(View.VISIBLE);
                mContactUsDetail.startAnimation(hideAnim);
                mContactUsDetail.setVisibility(View.INVISIBLE);
            }
        });

        mIvFacebookDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFacebook.setVisibility(View.VISIBLE);
                mFacebookDetail.startAnimation(hideAnim);
                mFacebookDetail.setVisibility(View.INVISIBLE);
            }
        });

        mIvPhoneDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhone.setVisibility(View.VISIBLE);
                mPhoneDetail.startAnimation(hideAnim);
                mPhoneDetail.setVisibility(View.INVISIBLE);
            }
        });

        mTvContactUsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContactUs.setVisibility(View.VISIBLE);
                mContactUsDetail.startAnimation(hideAnim);
                mContactUsDetail.setVisibility(View.INVISIBLE);
                startEmailComposer();
            }
        });

        mTvFacebookDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFacebook.setVisibility(View.VISIBLE);
                mFacebookDetail.startAnimation(hideAnim);
                mFacebookDetail.setVisibility(View.INVISIBLE);
                loadBrowser(Constants.FACEBOOK_URL);
            }
        });

        mTvPhoneDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhone.setVisibility(View.VISIBLE);
                mPhoneDetail.startAnimation(hideAnim);
                mPhoneDetail.setVisibility(View.INVISIBLE);
                startCallPhone();
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

        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, mTabLayout, false);

            View sep = relativeLayout.findViewById(R.id.sep);
            ImageView tabTextView = (ImageView) relativeLayout.findViewById(R.id.tab_icon);
            tabTextView.setImageDrawable(tab.getIcon());
            tab.setCustomView(relativeLayout);
            if(i==2){
                sep.setVisibility(View.GONE);
            }
        }
    }

    private void loadBrowser(String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void startEmailComposer()
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"contact@wmi.com.sg"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.setType("plain/text");
        startActivity(Intent.createChooser(intent, "Send Mail"));
    }

    private void startCallPhone()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+6568286988"));
        startActivity(intent);
    }
}
