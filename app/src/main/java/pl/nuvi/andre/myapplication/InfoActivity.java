package pl.nuvi.andre.myapplication;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView txtPrivacy = (TextView) findViewById(R.id.privacyPolicy);

        txtPrivacy.setPaintFlags(txtPrivacy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/dragonnamegenerator" ));
                startActivity(browserIntent);
            }
        });
    }
}
