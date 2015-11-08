package com.joe.root.feed;

/**
 * Created by JoeZ on 2015/10/16.
 */
public interface FeedUpdateListener {
    void onBinderCompile(FeedBinder binder);
    void onBindersCompileEnd();
    void onBindersCompileStart();
    void expandBinder(FeedBinder chioceBinder, FeedBinder resultBinder);
    void collapseBinder(FeedBinder resultBinder);
}
