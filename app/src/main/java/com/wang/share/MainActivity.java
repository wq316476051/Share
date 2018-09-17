package com.wang.share;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.wang.share.utils.FileUtils;
import com.wang.share.utils.ShareHelper;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends PermissionActivity implements View.OnClickListener {

    private Button mBtnShareText, mBtnShareFile, mBtnShareFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnShareText = findViewById(R.id.btn_share_text);
        mBtnShareFile = findViewById(R.id.btn_share_file);
        mBtnShareFiles = findViewById(R.id.btn_share_files);

        mBtnShareText.setOnClickListener(this);
        mBtnShareFile.setOnClickListener(this);
        mBtnShareFiles.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share_text:
                ShareHelper.create(this)
                        .setTitle("分享文本...")
                        .shareText("This is the message!");
                break;
            case R.id.btn_share_file:
                if (!isExternalStoragePermissionGranted()) {
                    requestExternalStoragePermission();
                    return;
                }

                File file = new File(Environment.getExternalStorageDirectory(), "aaa.txt");
                Uri uri = FileUtils.getFileUri(this, file.getAbsolutePath());

                ShareHelper.create(this)
                        .setTitle("分享文件...")
                        .setMimeType(ShareHelper.TYPE_FILE)
                        .shareFile(uri);
                break;
            case R.id.btn_share_files:
                if (!isExternalStoragePermissionGranted()) {
                    requestExternalStoragePermission();
                    return;
                }
                File file0 = new File(Environment.getExternalStorageDirectory(), "aaa.txt");
                Uri uri0 = FileUtils.getFileUri(this, file0.getAbsolutePath());

                File file1 = new File(Environment.getExternalStorageDirectory(), "bbb.txt");
                Uri uri1 = FileUtils.getFileUri(this, file1.getAbsolutePath());

                ArrayList<Uri> shareUris = new ArrayList<>();
                shareUris.add(uri0);
                shareUris.add(uri1);
                ShareHelper.create(this)
                        .setTitle("分享文件...")
                        .setMimeType(ShareHelper.TYPE_FILE)
                        .shareFiles(shareUris);
                break;
        }
    }
}
