package com.iretailer.util;

import java.util.Map;

/**
 * Created by clat on 2017/5/21.
 */
public class MapBuilder<T extends Map> {

    private T map;

    private MapBuilder(T t){
        map = t;
    }

    public MapBuilder map(String key, Object value){
        map.put(key,value);
        return this;
    }
    public static MapBuilder build(Map t){
        MapBuilder hb = new MapBuilder(t);
        return hb;
    }
    public T get(){
        return map;
    }
    public void clear(){
        map = null;
    }
}
