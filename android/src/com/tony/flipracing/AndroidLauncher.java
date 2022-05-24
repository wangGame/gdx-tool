package com.tony.flipracing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.doodlemobile.helper.AdsType;
import com.doodlemobile.helper.BannerConfig;
import com.doodlemobile.helper.BannerSize;
import com.doodlemobile.helper.DAdsConfig;
import com.doodlemobile.helper.DoodleAds;
import com.doodlemobile.helper.DoodleAdsListener;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.tony.rider.AdsAndShopListener;
import com.tony.rider.RiderGame;
import com.tony.rider.constant.Configuration;
import com.tony.rider.constant.Constant;
import com.tony.rider.flurry.FlurryUtils;
import com.tony.rider.log.NLog;
import com.tony.rider.preferences.RiderPreferences;

import java.math.BigDecimal;

public class AndroidLauncher extends AndroidApplication implements DoodleAdsListener {
    private ReviewManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDevice();
        manager = ReviewManagerFactory.create(this);
        DoodleAds.onCreate(this,this);

        initialize(new RiderGame(listener), new AndroidApplicationConfiguration(){{
            useCompass = false;
            useAccelerometer = false;
            useWakelock = true;
            numSamples = 5;
            if (Build.MODEL.equals("MediaPad 10 FHD")) {
                numSamples = 0;
            }
            if (Configuration.device_state != Configuration.DeviceState.poor) {
                r = 8;
                g = 8;
                b = 8;
            }
        }});
        initImmersiveMode();

        if (Constant.showJiaAds) {
            bannerAdsTest();
        }
    }

    private void bannerAdsTest() {
        LinearLayout adsLayoutDemo = new LinearLayout(this);
        adsLayoutDemo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        adsLayoutDemo.setOrientation(LinearLayout.VERTICAL);
        adsLayoutDemo.setGravity(Gravity.BOTTOM);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout imagebuttonLinearLayout = (LinearLayout) inflater.inflate(R.layout.adsdemo, adsLayoutDemo, false);
        adsLayoutDemo.addView(imagebuttonLinearLayout);
        addContentView(adsLayoutDemo,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int measuredHeight = imagebuttonLinearLayout.getHeight();
        NLog.d(" 高度 ：%s",measuredHeight);
    }

    @SuppressLint("NewApi")
    private void initImmersiveMode() {
        if (Build.VERSION.SDK_INT >= 19) {
            View.OnSystemUiVisibilityChangeListener listener = new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        enterImmersiveMode();
                    }
                }
            };
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(listener);
            enterImmersiveMode();
        }
    }
    private Runnable videoClosedRunnable;
    private AdsAndShopListener listener = new AdsAndShopListener() {
        @Override
        public void rate() {
            Intent intent;
            try{
                intent = new Intent(Intent.ACTION_VIEW);
                String uri = "market://details?id"+AndroidLauncher.this.getPackageName();
                intent.setData(Uri.parse(uri));
                AndroidLauncher.this.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                throw e;
            }
        }

        @Override
        public void showBanner(boolean visible) {
            if (visible){
                if (RiderPreferences.getPreferences().getLevel()>2){
                    if (!RiderPreferences.getPreferences().isShowBanner()){
                        RiderPreferences.getPreferences().saveShowBanner();
                        FlurryUtils.appearBanner();
                    }
                    DoodleAds.showBanner(visible);
                }
            }else {
                DoodleAds.showBanner(visible);
            }
        }

        @Override
        public boolean showInterstitialAds() {
            Constant.interstitial = System.currentTimeMillis();
            FlurryUtils.normal("clickNormal");
            if(DoodleAds.hasInterstitialAdsReady()) {
                FlurryUtils.normal("normalCompleted");
                DoodleAds.showInterstitial();
                return true;
            }
            return false;
        }


        @Override
        public void showVideo(Runnable runnable) {
            if (DoodleAds.isVideoAdsReady()){
                DoodleAds.showVideoAds();
                videoClosedRunnable = runnable;
            }else {
                videoClosedRunnable = null;
            }

        }

        public void setNullRunnable(){
            videoClosedRunnable = null;
        }

        @Override
        public void onVideoClosed() {
            FlurryUtils.movie("movieCompleted");
            if(videoClosedRunnable !=null){
                Gdx.app.postRunnable(videoClosedRunnable);
                //本次执行之后 ，将runnable请除掉。
                videoClosedRunnable = null;
            }
        }

        @Override
        public void newRate() {
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    NLog.e("playcore success");
//                    LevelData.instance.rateShowed();  //保存
                    ReviewInfo reviewInfo = task.getResult();
                    manager.launchReviewFlow(AndroidLauncher.this, reviewInfo);
                    Task<Void> flow = manager.launchReviewFlow(AndroidLauncher.this, reviewInfo);
                    flow.addOnCompleteListener(task1 -> {
                        NLog.e("flow success");
                    });
                    flow.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            NLog.e("flow fail ");
                        }
                    });
                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            NLog.e("flow success");
                        }
                    });
                }
            });
        }

        @Override
        public int getSdkInt() {
            return Build.VERSION.SDK_INT;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DoodleAds.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            enterImmersiveMode();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        graphics.getView().requestFocus();
        return super.dispatchKeyEvent(event);
    }


    public static double formatDouble(double d,int nscale){
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(nscale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private void initDevice() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Configuration.screen_width = metrics.widthPixels;
        Configuration.screen_height = metrics.heightPixels;
        float screenW=metrics.widthPixels,screenH=metrics.heightPixels;
//        float metrix=metrics.xdpi;
//        float metriy=metrics.ydpi;
//        float msw = screenW/metrix;
//        float msh = screenH/metriy;
//        double sqrt1 = Math.sqrt(msw * msw + msh * msh);
//        double chicun = formatDouble(sqrt1, 1);

//        double sqrt = Math.sqrt(screenW * screenW + screenH * screenH);
//        double v = sqrt / chicun;


        float min = Math.max(Constant.STDWIDTH/screenW,Constant.STDHIGHT/screenH);
        Constant.vvv = metrics.density*min;
//        NLog.d("尺寸  %s  比例  %s  结果 %s   dpi  %s",chicun,   v , Constant.vvv,metrics.densityDpi);
        NLog.d("结果 %s   dpi  %s",Constant.vvv,metrics.densityDpi);
        Configuration.bannerHeight = (int) dpTopx(50);


        NLog.e("screen size %s  %s",Configuration.screen_height , Configuration.screen_width);
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(outInfo);
        Configuration.device_name = Build.MODEL;
        Configuration.availableMem = (int) (outInfo.availMem / 1000000F);
        Configuration.APILevel = Build.VERSION.SDK_INT;
        Configuration.init_device();
    }

    public static float dpTopx(float dp){
        return (float) (dp*Constant.vvv + 0.5F);
    }

    @SuppressLint("NewApi")
    private void enterImmersiveMode() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        NotificationUtil.cancelAll(this);
        NotificationUtil.add(this);
        DoodleAds.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        DoodleAds.onResume();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public BannerConfig[] getAdmobBannerConfigs() {
//        return new BannerConfig[]{
//                new BannerConfig(AdsType.Admob, "ca-app-pub-3940256099942544/6300978111", true, BannerSize.BANNER)
//        };
        return new BannerConfig[]{
                new BannerConfig(AdsType.Admob, "ca-app-pub-3403243588104548/2061371141", true, BannerSize.BANNER)
        };
    }

    @Override
    public DAdsConfig[] getInterstitialConfigs() {
//        return new DAdsConfig[]{
//                /*A1*/new DAdsConfig(AdsType.Admob, "ca-app-pub-3940256099942544/1033173712"),
//        };
        return new DAdsConfig[]{
                /*A1*/new DAdsConfig(AdsType.Admob, "ca-app-pub-3403243588104548/9748289473"),
        };
    }


    @Override
    public void onInterstitialAdClosed() {

    }

    @Override
    public void onInterstitialAdLoaded() {

    }

    @Override
    public DAdsConfig[] getVideoAdsConfigs() {
//        return new DAdsConfig[]{
//                new DAdsConfig(AdsType.Admob, "ca-app-pub-3940256099942544/5224354917"),
//        };
        return new DAdsConfig[]{
                new DAdsConfig(AdsType.Admob, "ca-app-pub-3403243588104548/3577632790"),
        };

    }
    @Override
    public void onVideoAdsReady(AdsType type) {

    }

    @Override
    public void onVideoShowStart(AdsType type) {

    }

    @Override
    public void onVideoAdsClosed(AdsType type) {

    }

    @Override
    public void onVideoAdsSkipped(AdsType type) {

    }
}

