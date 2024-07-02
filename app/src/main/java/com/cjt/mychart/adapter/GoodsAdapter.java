package com.cjt.mychart.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjt.mychart.R;
import com.cjt.mychart.ShoppingDetailActivity;
import com.cjt.mychart.lean.GoodsInfo;
import com.cjt.mychart.util.ToastUtil;

import java.util.List;

public class GoodsAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private Context mContext;
    private List<GoodsInfo> mGoodsList;

    public GoodsAdapter(Context context,List<GoodsInfo> goods_list,addCartListener addCartListener){
        mContext = context;
        mGoodsList = goods_list;
        mAddCartListener = addCartListener;
    }

    @Override
    public int getCount() {
        return mGoodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods,null);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.iv_thumb = convertView.findViewById(R.id.iv_thumb);
            holder.tv_price = convertView.findViewById(R.id.tv_price);
            holder.btn_add = convertView.findViewById(R.id.btn_add);
            // 将视图持有者保存到转换视图当中
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GoodsInfo goods = mGoodsList.get(position);
        holder.tv_name.setText(goods.name);
        holder.iv_thumb.setImageURI(Uri.parse(goods.pic_path));
        holder.tv_price.setText(""+(int)goods.price);
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddCartListener.addToCart(goods.rowid, goods.name);
                ToastUtil.show(mContext, "已添加一部" + goods.name + "到购物车");
            }
        });
        return convertView;
    }

    public final class ViewHolder {
        public TextView tv_name; // 声明商品名称的文本视图对象
        public ImageView iv_thumb; // 声明商品图片的图像视图对象
        public TextView tv_price; // 声明商品价格的文本视图对象
        public Button btn_add; // 声明加入购物车的按钮对象
    }

    // 声明一个加入购物车的监听器对象
    private addCartListener mAddCartListener;
    // 定义一个加入购物车的监听器接口
    public interface addCartListener {
        void addToCart(long goods_id, String goods_name);  // 在商品加入购物车时触发
    }
    // 处理列表项的点击事件，由接口OnItemClickListener触发
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsInfo goods = mGoodsList.get(position);
        // 携带商品编号跳转到商品详情页面
        Intent intent = new Intent(mContext, ShoppingDetailActivity.class);
        intent.putExtra("goods_id", goods.rowid);
        mContext.startActivity(intent);
    }
}
