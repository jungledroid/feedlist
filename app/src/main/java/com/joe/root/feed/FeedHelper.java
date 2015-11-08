package com.joe.root.feed;

import com.google.gson.Gson;
import com.joe.root.feed.group.ArticlePartDefine;
import com.joe.root.feed.group.FeedGroupPartDefine;
import com.joe.root.feed.group.ListPartDefine;
import com.joe.root.feed.partdefine.ListChoiceSingleDefine;
import com.joe.root.listener.Callback;
import com.joe.root.model.Card;
import com.joe.root.model.ListCard;
import com.joe.root.model.ArticleCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/10/9.
 */
public class FeedHelper {
    private JSONArray mFeedJsonArray;
    private static FeedHelper mFeedParsers = new FeedHelper();
    private Gson mGson = new Gson();
    private boolean mIsLoading;
    private List<Callback<JSONArray>> mFeedCallbacks = new ArrayList<Callback<JSONArray>>(2);

    private FeedHelper() {

    }

    public static FeedHelper getInstance() {
        return mFeedParsers;
    }

    public void notifyPollCheckChanged(int groupPosition,ListCard pollCard){
        if(mFeedJsonArray.length() > groupPosition){
            try {
                mFeedJsonArray.put(groupPosition, new JSONObject(mGson.toJson(pollCard)));
            } catch (JSONException e) {
            }
        }
    }

    public void clear() {
        mFeedJsonArray = null;
    }

    public void parser(JSONObject json, String type,int groupPosition, FeedUpdateListener feedUpdateListener) {
        Card card = null;
        FeedGroupPartDefine feedGroupPartDefine = null;
        if (ListCard.getTypeCode().equals(type)) {
            card = mGson.fromJson(json.toString(), ListCard.class);
            feedGroupPartDefine = new ListPartDefine();
        } else if (ArticleCard.getTypeCode().equals(type)) {
            card = mGson.fromJson(json.toString(), ArticleCard.class);
            feedGroupPartDefine = new ArticlePartDefine();
        }
        if (feedGroupPartDefine != null) {
            feedGroupPartDefine.setGroupPosition(groupPosition);
            feedGroupPartDefine.compileBinders(card, feedUpdateListener);
        }
    }

    public void parseFeedBinders(JSONArray jsonArray, FeedUpdateListener feedUpdateListener) {
        mFeedJsonArray = jsonArray;
        onFinishedLoading();
        int length = jsonArray.length();
        JSONObject jsonCard;
        if (feedUpdateListener != null) {
            feedUpdateListener.onBindersCompileStart();
        }
        for (int index = 0; index < length; index++) {
            try {
                jsonCard = jsonArray.getJSONObject(index);
                parser(jsonCard, jsonCard.getString("type"),index, feedUpdateListener);
            } catch (JSONException e) {

            }
        }
        if (feedUpdateListener != null) {
            feedUpdateListener.onBindersCompileEnd();
        }
    }

    public void refreshFeeds(Callback<JSONArray> feedCallback){
        if(mIsLoading){
            mFeedCallbacks.add(feedCallback);
            return;
        }
        if(mFeedJsonArray == null){
            JSONArray jsonArray = new JSONArray();
            try {
                jsonArray.put(new JSONObject(ArticlePartDefine.TEST_JSON_UMC));
                jsonArray.put(new JSONObject(ListChoiceSingleDefine.TEST_JSON_OPTIONS_TEXT));
                jsonArray.put(new JSONObject(ArticlePartDefine.TEST_JSON_UMC));
                jsonArray.put(new JSONObject(ListChoiceSingleDefine.TEST_JSON_OPTIONS_TEXT));
                jsonArray.put(new JSONObject(ArticlePartDefine.TEST_JSON_UMC));
                jsonArray.put(new JSONObject(ListChoiceSingleDefine.TEST_JSON_OPTIONS_TEXT));
                jsonArray.put(new JSONObject(ArticlePartDefine.TEST_JSON_UMC));
                jsonArray.put(new JSONObject(ListChoiceSingleDefine.TEST_JSON_OPTIONS_TEXT));
                mFeedJsonArray = jsonArray;
            } catch (JSONException e) {
            }
        }
        feedCallback.success(mFeedJsonArray);

    }

    private void onFinishedLoading(){
        mIsLoading = false;
        for (Callback<JSONArray> mFeedCallback : mFeedCallbacks) {
            mFeedCallback.success(mFeedJsonArray);
        }
        mFeedCallbacks.clear();
    }
}
