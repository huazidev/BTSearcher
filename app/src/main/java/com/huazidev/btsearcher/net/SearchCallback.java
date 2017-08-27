package com.huazidev.btsearcher.net;

import com.huazidev.btsearcher.data.SearchModel;

import java.util.List;

/**
 * @author hua on 2017/8/21.
 */
public interface SearchCallback {
    void onSuccess(List<SearchModel> searchList);

    void onFailure();
}
