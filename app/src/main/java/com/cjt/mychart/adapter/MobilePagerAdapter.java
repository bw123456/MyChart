package com.cjt.mychart.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cjt.mychart.Fragment.DynamicFragment1;
import com.cjt.mychart.lean.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class MobilePagerAdapter extends FragmentPagerAdapter {
    private List<GoodsInfo> mGoodsList = new ArrayList<GoodsInfo>();

    public MobilePagerAdapter(FragmentManager fm,List<GoodsInfo> goodsList) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mGoodsList = goodsList;
    }

    @Override
    public int getCount(){
        return mGoodsList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DynamicFragment1.newInstance(position,
                mGoodsList.get(position).pic,mGoodsList.get(position).desc);
    }

    public CharSequence getPageTitle(int position) {
        return mGoodsList.get(position).name;
    }

}
