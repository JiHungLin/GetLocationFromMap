# GetLocationFromMap

GetLocationFromMap 是一個使用 Google Maps SDK 的 Android 應用程式，允許用戶從地圖上獲取位置。

## 功能

- 獲取精確位置
- 獲取粗略位置
- 顯示 Google 地圖

## 設置

1. 前往 [Google Cloud Console](https://console.cloud.google.com/) 並創建一個新專案。
2. 啟用 Maps SDK for Android。
3. 創建一個 API 金鑰並將其添加到 `res/values/strings.xml` 文件中：

    ```xml
    <string name="google_map_key">YOUR_API_KEY_HERE</string>
    ```

## 權限

請確保在 `AndroidManifest.xml` 中添加了以下權限：

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
