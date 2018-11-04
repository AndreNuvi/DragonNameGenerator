package pl.nuvi.andre.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class DragonName extends AppCompatActivity implements RewardedVideoAdListener {
    //True AD
    private static final String APP_ID = "ca-app-pub-4844192903995686~1540537457";
    private static final String AD_UNIT_ID = "ca-app-pub-4844192903995686/9344789052";



    TextView dragonNameText;
    TextView dovakinNameText;
    TextView dovakinTitle;
    ImageView dragonImage;
    WebView sandaraWebView;
    String dragonName;
    TextView videoBanner;
    ProgressBar progressBar;
    private RewardedVideoAd mRewardedVideoAd;
    boolean rewardGain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_name);
        dragonNameText = (TextView) findViewById(R.id.dragonNameTextViewActivityDragonNameId);
        dragonImage = (ImageView) findViewById(R.id.imageViewActivityDragonNameId);
        dovakinNameText = (TextView) findViewById(R.id.dovakinText);
        dovakinTitle = (TextView) findViewById(R.id.dovakinTitle);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        // //Initialize the reward ads
        //  MobileAds.initialize(this, APP_ID);

        Typeface typeFaceName = Typeface.createFromAsset(getAssets(), "fonts/ArgosGeorge.ttf");
        dragonNameText.setTypeface(typeFaceName);


        //Set reward ad
        videoBanner = (TextView) findViewById(R.id.dovakinBanner);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();


        videoBanner.setVisibility(View.INVISIBLE);


        videoBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }

            }
        });


        //WebView operation
        sandaraWebView = (WebView) findViewById(R.id.sandara);
        String sandaraStyle = "<style>  a{ color: white; text-decoration: none; font-size: 14px}</style>";
        String sandaraLink = "<p align=\"right\"><a href=\"http://sandara.deviantart.com/\">sandara.deviantart.com</a></p>";
        String sandaraOutput = sandaraStyle + sandaraLink;
        sandaraWebView.loadDataWithBaseURL(null, sandaraOutput, "text/html", "UTF-8", null);
        sandaraWebView.setBackgroundColor(Color.TRANSPARENT);

        Intent in = this.getIntent();
        dragonName = in.getStringExtra("DragonName");
        dragonNameText.setText(dragonName);


        String lastLet = dragonName.substring(2, 3);

        switch (lastLet) {
            case "a":
                dragonImage.setImageResource(R.drawable.smok01);
                break;
            case "b":
                dragonImage.setImageResource(R.drawable.smok02);
                break;
            case "c":
                dragonImage.setImageResource(R.drawable.smok03);
                break;
            case "d":
                dragonImage.setImageResource(R.drawable.smok04);
                break;
            case "e":
                dragonImage.setImageResource(R.drawable.smok04);
                break;
            case "f":
                dragonImage.setImageResource(R.drawable.smok05);
                break;
            case "g":
                dragonImage.setImageResource(R.drawable.smok06);
                break;
            case "h":
                dragonImage.setImageResource(R.drawable.smok07);
                break;
            case "i":
                dragonImage.setImageResource(R.drawable.smok08);
                break;
            case "j":
                dragonImage.setImageResource(R.drawable.smok09);
                break;
            case "k":
                dragonImage.setImageResource(R.drawable.smok10);
                break;
            case "l":
                dragonImage.setImageResource(R.drawable.smok11);
                break;
            case "m":
                dragonImage.setImageResource(R.drawable.smok12);
                break;
            case "n":
                dragonImage.setImageResource(R.drawable.smok13);
                break;
            case "o":
                dragonImage.setImageResource(R.drawable.smok14);
                break;
            case "p":
                dragonImage.setImageResource(R.drawable.smok15);
                break;
            case "r":
                dragonImage.setImageResource(R.drawable.smok16);
                break;
            case "s":
                dragonImage.setImageResource(R.drawable.smok17);
                break;
            case "t":
                dragonImage.setImageResource(R.drawable.smok18);
                break;
            case "u":
                dragonImage.setImageResource(R.drawable.smok19);
                break;
            case "w":
                dragonImage.setImageResource(R.drawable.smok20);
                break;
            case "x":
                dragonImage.setImageResource(R.drawable.smok21);
                break;
            case "y":
            case "z":
                dragonImage.setImageResource(R.drawable.smok22);
                break;
        }


        // Read if you have reward
        SharedPreferences readReward = getSharedPreferences("Reward", MODE_PRIVATE);
        rewardGain = readReward.getBoolean("Reward", false);

        if (rewardGain==true) {
            setReward();


        } else {
            dovakinNameText.setVisibility(View.GONE);
            dovakinTitle.setVisibility(View.GONE);

        }

    }


    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.resume(this);
        super.onDestroy();
    }


    private void loadRewardedVideoAd() {
        //if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
       // }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        progressBar.setVisibility(View.GONE);

        if (rewardGain == false) {
            videoBanner.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
    }

    @Override
    public void onRewardedVideoAdClosed() {
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

        //Write boolen reward
        boolean reward = true;
        SharedPreferences.Editor editor = getSharedPreferences("Reward", MODE_PRIVATE).edit();
        editor.putBoolean("Reward", reward);
        editor.apply();

       setReward();


    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    public void restartActivity(){
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
    }

    public void setReward(){
        //Set font to dovakin text
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/dragon_alphabet.ttf");
        dovakinNameText.setTypeface(typeFace);

        dovakinNameText.setVisibility(View.VISIBLE);
        dovakinTitle.setVisibility(View.VISIBLE);
        videoBanner.setVisibility(View.GONE);

        dovakinNameText.setText(dragonName);

    }
}
