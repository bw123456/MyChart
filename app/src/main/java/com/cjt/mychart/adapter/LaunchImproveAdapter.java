package com.cjt.mychart.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cjt.mychart.Fragment.LaunchFragment;

public class LaunchImproveAdapter extends FragmentPagerAdapter {
    private int[] mImageArray;
    public LaunchImproveAdapter(@NonNull FragmentManager fm,int[] imageArray) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mImageArray = imageArray;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return LaunchFragment.newInstance(position,mImageArray[position]);
    }

    @Override
    public int getCount() {
        return mImageArray.length;
    }
}
