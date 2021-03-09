package com.example.shiwuyouproject.ui.fragment.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.shiwuyouproject.R;
import com.example.shiwuyouproject.ui.fragment.home.bean.homeBean;

import java.util.List;

public class homeAdapter  extends BaseQuickAdapter<homeBean, BaseViewHolder> {
    public homeAdapter(List<homeBean> data) {
        super(R.layout.item_home, data);
    }
    @Override
    protected void convert(BaseViewHolder helper,homeBean item) {

    }
}