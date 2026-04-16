package com.example.sportsfeed;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BookmarkManager {

    private static final String PREFS_NAME = "bookmarks_prefs";
    private static final String KEY_BOOKMARKS = "bookmarks";

    public static void saveBookmark(Context context, NewsItem item) {
        List<NewsItem> current = getBookmarks(context);
        for (NewsItem b : current) {
            if (b.getId() == item.getId()) return; // already saved
        }
        current.add(item);
        saveAll(context, current);
    }

    public static void removeBookmark(Context context, int itemId) {
        List<NewsItem> current = getBookmarks(context);
        current.removeIf(item -> item.getId() == itemId);
        saveAll(context, current);
    }

    public static boolean isBookmarked(Context context, int itemId) {
        for (NewsItem item : getBookmarks(context)) {
            if (item.getId() == itemId) return true;
        }
        return false;
    }

    public static List<NewsItem> getBookmarks(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_BOOKMARKS, "[]");
        List<NewsItem> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                list.add(new NewsItem(
                        obj.getInt("id"),
                        obj.getString("title"),
                        obj.getString("description"),
                        obj.getString("category"),
                        obj.getInt("imageResId")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void saveAll(Context context, List<NewsItem> items) {
        JSONArray array = new JSONArray();
        for (NewsItem item : items) {
            try {
                JSONObject obj = new JSONObject();
                obj.put("id", item.getId());
                obj.put("title", item.getTitle());
                obj.put("description", item.getDescription());
                obj.put("category", item.getCategory());
                obj.put("imageResId", item.getImageResId());
                array.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putString(KEY_BOOKMARKS, array.toString()).apply();
    }
}