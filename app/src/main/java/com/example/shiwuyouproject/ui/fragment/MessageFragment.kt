package com.example.shiwuyouproject.ui.fragment

import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentMessageBinding
import com.example.shiwuyouproject.ui.fragment.message.adapter.MessageAdapter
import com.example.shiwuyouproject.ui.fragment.message.bean.MessageBean
import com.example.shiwuyouproject.ui.modelview.MessageViewModel
import java.util.*

class MessageFragment:BaseVmFragment<FragmentMessageBinding, MessageViewModel>() {
    override fun viewModelClass(): Class<MessageViewModel> =MessageViewModel::class.java
    override fun getViewBinding(): FragmentMessageBinding {
        return FragmentMessageBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }
    private var adapter: MessageAdapter? = null
    var datas: MutableList<MessageBean> = ArrayList<MessageBean>()
    override fun initData() {
        for (i in 0..2) {
            datas.add(MessageBean())
        }
        adapter = MessageAdapter(datas)
        binding?.myMessageRecyclerView?.adapter = adapter
    }

    override fun setListener() {

    }

}