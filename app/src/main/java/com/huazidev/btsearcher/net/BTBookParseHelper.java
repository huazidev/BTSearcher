package com.huazidev.btsearcher.net;

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
 * @author hua on 2017/8/21.
 */
public class BTBookParseHelper {

    private static final String ROOT_URL = "http://www.btwhat.net/";
    private static final String SEARCH_BASE_URL = ROOT_URL + "search/";

    public static void getSearchList(String searchStr, int page, final SearchCallback callback) {
        final String url;
        if (page <= 1) {
            url = SEARCH_BASE_URL + searchStr;
        } else {
            url = SEARCH_BASE_URL + searchStr + "/" + page;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
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
            searchModel.url = ROOT_URL + titleElement.select("a").attr("href");
            searchModel.name = titleElement.text();
            Timber.d("The Item name:=" + searchModel.name);
            Timber.d("The item Url=:" + searchModel.url);
            Element itemBarElement = element.getElementsByClass("item-bar").get(0);
            searchModel.time = itemBarElement.getElementsByTag("span").get(1).text();
            searchModel.size = itemBarElement.getElementsByTag("span").get(2).text();
            Timber.d("The item time=:" + searchModel.time);
            Timber.d("The item size=:" + searchModel.size);
            searchModel.type = element.getElementsByClass("cpill fileType1").text();
            Timber.d("The item type=:" + element.getElementsByClass("cpill fileType1").text());

            searchModelList.add(searchModel);
        }
        return searchModelList;

    }

}
