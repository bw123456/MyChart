package com.cjt.mychart.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cjt.mychart.R;

public class LaunchFragment extends Fragment {

    protected View mView;
    protected Context mContext;
    private int mPosition;
    private int mImagerId;
    private int mCount = 4;

    public LaunchFragment() {
        // Required empty public constructor
    }


    public static LaunchFragment newInstance(int position, int image_id) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        if (getArguments() != null){
            mPosition = getArguments().getInt("position",0);
            mImagerId = getArguments().getInt("image_id",0);
        }
        mView = inflater.inflate(R.layout.item_launch,container,false);
        ImageView iv_launch = mView.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = mView.findViewById(R.id.rg_indicate);
        Button btn_start = mView.findViewById(R.id.btn_start);
        iv_launch.setImageResource(mImagerId);
        for (int j=0;j<mCount;j++){
            RadioButton radio = new RadioButton(mContext);
            radio.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            radio.setButtonDrawable(R.drawable.launch_guide);
            radio.setPadding(10,10,10,10);
            rg_indicate.addView(radio);
        }
        ((RadioButton)rg_indicate.getChildAt(mPosition)).setChecked(true);

        if (mPosition == mCount-1){
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"欢迎您开启美好生活",Toast.LENGTH_SHORT).show();
                }
            });
        }
        return mView;
    }
}