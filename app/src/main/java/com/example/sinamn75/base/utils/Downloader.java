package com.example.sinamn75.base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;

import com.example.sinamn75.base.base.BaseActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.Objects;

public abstract class Downloader extends BaseActivity {
    private Uri downloadUri;
    private Context context;
    private String NameFile;
    private File exist_file;
    private BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {
            intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            onAddresFile(exist_file);
        }
    };

    protected Downloader(Context context, Activity activity, String url, String namefile) {
        this.context = context;
        this.downloadUri = Uri.parse(url);
        this.NameFile = namefile;

        AndPermission.with(activity).runtime().permission(Permission.Group.STORAGE)
                .onGranted(permissions -> init())
                .onDenied(permissions -> toastError("error"))
                .start();
    }

    private void init() {
        @SuppressLint("SdCardPath") File create = new File("/sdcard/Folder/");
        exist_file = new File(create.getPath() + "/" + NameFile);
        if (exist_file.exists()) {
            onAddresFile(exist_file);
        } else {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            toastNormal("downloading");
            DownloadManager.Request request = new DownloadManager.Request(downloadUri).setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false).setTitle("file...")
                    .setDescription("downloading...")
                    .setVisibleInDownloadsUi(false)
                    .setDestinationInExternalPublicDir("Folder/", NameFile);
            Objects.requireNonNull(downloadManager).enqueue(request);
        }
    }
    public abstract void onAddresFile(File address);
}