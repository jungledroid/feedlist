package com.joe.root.listener;

/**
 * Created by JoeZ on 2015/10/10.
 */

public interface Callback<T>{
    void success(T array);
    void failure();
}
