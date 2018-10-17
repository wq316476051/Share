# Share

## 发送分享
Using:
```
ShareHelper.create(this)
    .setTitle("分享文本...")
    .shareText("This is the message!");
```


```
File file = new File(Environment.getExternalStorageDirectory(), "aaa.txt");
Uri uri = FileUtils.getFileUri(this, file.getAbsolutePath());

ShareHelper.create(this)
    .setTitle("分享文件...")
    .setMimeType(ShareHelper.TYPE_FILE)
    .shareFile(uri);
```


```
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
```

## 接收分享
```
<activity >
    <intent-filter>
        // Uri.fromFile()
    </intent-filter>
    
    <intent-filter>
        // Uri from MediaProvider-database
    </intent-filter>
    
    <intent-filter>
        // Uri from FileProvider
    </intent-filter>
</activity>
```

