package com.huazidev.btsearcher.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.huazidev.btsearcher.R;
import com.huazidev.btsearcher.common.Extra;
import com.huazidev.btsearcher.data.SearchModel;
import com.huazidev.btsearcher.net.SOBTParseHelper;
import com.huazidev.btsearcher.net.SearchCallback;
import com.huazidev.btsearcher.util.ToastUtils;
import com.huazidev.btsearcher.view.HRecyclerView;

import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.recycler_view) HRecyclerView hRecyclerView;

    private MultiTypeAdapter adapter;
    private Items dataList;

    private String searchStr;

    private int page = 1;

    public static void start(Context context, String searchStr) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(Extra.SEARCH_STR, searchStr);
        context.startActivity(starter);
    }

    @Override
    protected void initVariables() {
        searchStr = getIntent().getStringExtra(Extra.SEARCH_STR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupHRecyclerView();
    }

    @Override
    protected void loadData() {
        getData(searchStr, page);
    }

    private void setupHRecyclerView() {
        adapter = new MultiTypeAdapter();
        dataList = new Items();
        adapter.setItems(dataList);
        adapter.register(SearchModel.class, new SearchItemViewBinder());
        hRecyclerView.setAdapter(adapter);
        hRecyclerView.setRefreshLoadListener(new HRecyclerView.OnRefreshLoadListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData(searchStr, page);
            }

            @Override
            public void onLoadMore() {
                page++;
                getData(searchStr, page);
            }
        });
    }

    public void getData(String searchKey, final int page) {
        SOBTParseHelper.getSearchList(searchKey, page, new SearchCallback() {
            @Override
            public void onSuccess(List<SearchModel> searchList) {
                if (page == 1) {
                    hRecyclerView.setRefreshing(false);
                    dataList.clear();
                    dataList.addAll(searchList);
                    adapter.notifyDataSetChanged();
                } else {
                    int start = dataList.size();
                    dataList.addAll(searchList);
                    adapter.notifyItemRangeChanged(start, dataList.size());
                }

            }

            @Override
            public void onFailure() {
                ToastUtils.shortT(R.string.load_data_error);
            }
        });
    }

}
