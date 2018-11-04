package pl.nuvi.andre.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    String yourName;
    String yourLastName;
    String yourMotherName;
    String yourFatherName;
    String dragonName;

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Button buttonGenerate;
    Button buttonReset;
    InterstitialAd mInterstitialAd;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_info:
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
            default:
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.yourNameEditTextActivityMainId);
        editText2 = (EditText) findViewById(R.id.yourSurnameEditTextActivityMainId);
        editText3 = (EditText) findViewById(R.id.yourFatherNameEditTextActivityMainId);
        editText4 = (EditText) findViewById(R.id.yourMotherNameActivityMainId);
        buttonGenerate = (Button) findViewById(R.id.checkYourDragonNameButtonActivityMainID);
        buttonReset = (Button) findViewById(R.id.resetButtonActivityMainID);


        //Banner Ad
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4844192903995686~1540537457");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Interstitial Ad between this and nex Activity
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId("ca-app-pub-4844192903995686/1461595450");
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                requestNewInterstitial();
//                beginGenerateDragonName();
//            }
//        });
//        requestNewInterstitial();


        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Turn of interstitial Add

                //TODO Make ads every few clicks

//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                } else {
                    beginGenerateDragonName();
//                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void beginGenerateDragonName() {
        yourName = editText1.getText().toString();
        yourLastName = editText2.getText().toString();
        yourFatherName = editText3.getText().toString();
        yourMotherName = editText4.getText().toString();

        if (yourName.length() == 0) {
            Toast.makeText(MainActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
        } else if (yourLastName.length() == 0) {
            Toast.makeText(MainActivity.this, "Enter your last name", Toast.LENGTH_SHORT).show();
        } else if (yourFatherName.length() == 0) {
            Toast.makeText(MainActivity.this, "Enter your father name", Toast.LENGTH_SHORT).show();
        } else if (yourMotherName.length() == 0) {
            Toast.makeText(MainActivity.this, "Enter your mother name", Toast.LENGTH_SHORT).show();

        } else if (yourName.length() == 1) {
            Toast.makeText(MainActivity.this, "You have to write at least two letters in your name", Toast.LENGTH_SHORT).show();
        } else if (yourLastName.length() == 1) {
            Toast.makeText(MainActivity.this, "You have to write at least two letters in your last name", Toast.LENGTH_SHORT).show();
        } else if (yourFatherName.length() == 1) {
            Toast.makeText(MainActivity.this, "You have to write at least two letters in your father name", Toast.LENGTH_SHORT).show();
        } else if (yourMotherName.length() == 1) {
            Toast.makeText(MainActivity.this, "You have to write at least two letters in your mother name", Toast.LENGTH_SHORT).show();
        } else {

            String lastLettersOfYourName = yourName.substring(yourName.length() - 1);
            String lastSecondLettersOfYourName = yourName.substring(yourName.length() - 2, yourName.length() - 1);
            String middleTwoLettersOfYourLastName = yourLastName.substring((yourLastName.length() / 2) - 1, (yourLastName.length() / 2) + 1);
            String firstTwoLettersOfYourMotherName = yourMotherName.substring(0, 2);
            String lastLetterOfYourFatherName = yourFatherName.substring(yourFatherName.length() - 1);

            dragonName = lastSecondLettersOfYourName.toUpperCase() + lastLettersOfYourName + middleTwoLettersOfYourLastName + firstTwoLettersOfYourMotherName.toLowerCase() + lastLetterOfYourFatherName;


            //Send intent
            Intent intent = new Intent(MainActivity.this, DragonName.class);
            intent.putExtra("DragonName", dragonName + "");
            startActivity(intent);
        }

    }

    @Override
    protected void onStart() {
        //Reward ad boolean reset
        boolean reward = false;
        SharedPreferences.Editor editor = getSharedPreferences("Reward", MODE_PRIVATE).edit();
        editor.putBoolean("Reward", reward);
        editor.apply();
        super.onStart();
    }
}
