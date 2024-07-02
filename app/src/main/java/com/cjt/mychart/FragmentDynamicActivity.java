package com.cjt.mychart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import com.cjt.mychart.adapter.MobilePagerAdapter;
import com.cjt.mychart.lean.GoodsInfo;

import java.util.List;

public class FragmentDynamicActivity extends AppCompatActivity {
    private static final String TAG = "FragmentDynamicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dynamic);
        initPagerStrip();
        initViewPager();
    }

    private void initPagerStrip(){
        PagerTabStrip pts_tab = findViewById(R.id.pts_tab);
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        pts_tab.setTextColor(Color.BLACK);
    }

    private void initViewPager(){
        List<GoodsInfo> goodsList = GoodsInfo.getDefaultFist();
        MobilePagerAdapter adapter = new MobilePagerAdapter(
                getSupportFragmentManager(),goodsList);
        ViewPager vp_content = findViewById(R.id.vp_content);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(0);

    }
}