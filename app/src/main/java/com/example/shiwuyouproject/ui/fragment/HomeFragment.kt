package com.example.shiwuyouproject.ui.fragment
import com.example.shiwuyouproject.R
import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentHomeBinding
import com.example.shiwuyouproject.ui.fragment.home.adapter.homeAdapter
import com.example.shiwuyouproject.ui.fragment.home.bean.homeBean
import com.example.shiwuyouproject.ui.modelview.HomeViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import java.util.*

/**
 * 首页
 */
class HomeFragment :BaseVmFragment<FragmentHomeBinding, HomeViewModel>(){
    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getViewBinding(): FragmentHomeBinding {return FragmentHomeBinding.inflate(
        layoutInflater
    )}

    override fun initView() {

    }
    var StimgList: MutableList<Int> = ArrayList()
    var datas: MutableList<homeBean> = ArrayList()
    private var adapter: homeAdapter? = null
    override fun initData() {
        StimgList.add(R.mipmap.icon_home_test1)
        StimgList.add(R.mipmap.icon_home_test1)
        StimgList.add(R.mipmap.icon_home_test1)
        binding?.myBanner?.setDatas(StimgList)
        binding?.myBanner?.start()
        binding?.refreshLayout?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {

            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {

            }
        })
        adapter = homeAdapter(datas)
        datas.add(homeBean())
        datas.add(homeBean())
        datas.add(homeBean())
        binding?.homeRecyclerView?.adapter = adapter

    }
}