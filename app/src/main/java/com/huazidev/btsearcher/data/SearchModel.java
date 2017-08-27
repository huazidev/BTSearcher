package com.huazidev.btsearcher.data;

/**
 * @author hua on 2017/8/21.
 */
public class SearchModel {
    public static final String MAGNET_HEAD = "magnet:?xt=urn:btih:";

    public String name;
    public String url;
    public String type;
    public String time;
    public String magnet;
    public String size;
    public String hot;

    @Override
    public String toString() {
        return "{\"hot\":\"" + hot + "\",\"magnet\":\"" + magnet + "\",\"name\":\"" + name + "\",\"size\":\"" + size + "\",\"time\":\"" + time + "\",\"url\":\"" + url + "\"}";
    }
}

