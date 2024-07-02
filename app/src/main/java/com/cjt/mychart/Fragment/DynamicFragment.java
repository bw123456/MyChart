package com.cjt.mychart.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cjt.mychart.R;

public class DynamicFragment extends Fragment {
    public static DynamicFragment newInstance(int position,int image_id,String desc){
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        args.putInt("image_id",image_id);
        args.putString("desc",desc);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dynamic, container, false);
        Bundle arguments = getArguments();
        if (arguments!= null){
            ImageView iv_pic = view.findViewById(R.id.iv_pic);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            iv_pic.setImageResource(arguments.getInt("image_id",R.drawable.huawei));
            tv_desc.setText(arguments.getString("desc"));
        }
        return view;
    }
}
