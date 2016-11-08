package com.beautiful.beautiful.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.beautiful.beautiful.mvp.model.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krkj on 2016/11/8.
 */

public class VideoProvider {
    private Context context;

    public VideoProvider(Context context) {
        this.context = context;
    }

    public List<VideoInfo> getList() {
        List<VideoInfo> list = null;
        if (context != null) {
            Cursor cursor = context
                    .getContentResolver()
                    .query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            null, null, null, null);

            if (cursor != null) {
                list = new ArrayList<>();
                while (cursor.moveToNext()) {
                    int id = cursor
                            .getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    String title = cursor
                            .getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    String album = cursor
                            .getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
                    String artist = cursor
                            .getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
                    String displayName = cursor
                            .getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                    String mimeType = cursor
                            .getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                    String path = cursor
                            .getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    long duration = cursor
                            .getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                    long size = cursor
                            .getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));

                    VideoInfo video = new VideoInfo(id, title, album,
                                            artist, displayName, mimeType,
                                            path, size, duration);
                    list.add(video);
                }
                cursor.close();
            }
        }
        return list;
    }
}
