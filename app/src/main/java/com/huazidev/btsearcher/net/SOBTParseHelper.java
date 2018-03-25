package com.huazidev.btsearcher.net;

import com.huazidev.btsearcher.R;
import com.huazidev.btsearcher.common.ResourceHelper;
import com.huazidev.btsearcher.data.SearchModel;
import com.huazidev.btsearcher.util.AndroidUtilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * @author hua on 2017/8/27.
 */
public class SOBTParseHelper {
    private static final String ROOT_URL = "https://www.btmule.org";

    public static void getSearchList(String searchStr, int page, final SearchCallback callback) {
        final String url = ResourceHelper.getString(R.string.sobt_search_url, ROOT_URL, searchStr, page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).timeout(30 * 1000).userAgent("Mozilla").get();
                    final List<SearchModel> searchList = getSearchItems(doc);
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(searchList);
                        }
                    });
                } catch (IOException e) {
                    callback.onFailure();
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private static List<SearchModel> getSearchItems(Document doc) {
        List<SearchModel> searchModelList = new ArrayList<>();
        Elements elements = doc.getElementsByClass("search-item");
        for (Element element : elements) {
            SearchModel searchModel = new SearchModel();
            Element titleElement = element.getElementsByClass("item-title").first();
            searchModel.name = titleElement.text();

            String sub_url = titleElement.select("a").attr("href");
            searchModel.url = ROOT_URL + sub_url;
            String hash = sub_url.substring(9, sub_url.length() - 5);
            searchModel.magnet = SearchModel.MAGNET_HEAD + hash;

            Elements searchBarItems = element.getElementsByTag("b");
            for (int i = 0; i < searchBarItems.size(); i++) {
                if (i == 0) {
                    searchModel.time = searchBarItems.get(0).text();
                } else if (i == 1) {

                    searchModel.size = searchBarItems.get(1).text();
                } else if (i == 2) {

                    searchModel.hot = searchBarItems.get(2).text();
                }
            }
            Timber.d("SearchResult = " + searchModel.toString());
            searchModelList.add(searchModel);
        }
        return searchModelList;

    }

}
