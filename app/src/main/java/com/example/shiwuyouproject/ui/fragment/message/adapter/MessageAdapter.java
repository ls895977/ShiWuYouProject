package com.example.shiwuyouproject.ui.fragment.message.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.shiwuyouproject.R;
import com.example.shiwuyouproject.ui.fragment.message.bean.MessageBean;

import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public MessageAdapter(List<MessageBean> data) {
        super(R.layout.item_message, data);
    }
    @Override
    protected void convert(BaseViewHolder helper,MessageBean item) {

    }
}