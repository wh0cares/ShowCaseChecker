package com.lovejoy777.showcasechecker;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lovejoy777 on 24/06/15.
 */

public class Details extends AppCompatActivity {

    final ImageView ScreenshotimageView[] = new ImageView[3];
    Bitmap bitmap[] = new Bitmap[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        // Handle ToolBar
        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        // Get String SZP
        final Intent extras = getIntent();

        // Get Strings
        String title = extras.getStringExtra("keytitle");
        final String link = extras.getStringExtra("keylink");
        final String googleplus = extras.getStringExtra("keygoogleplus");
        final String promo = extras.getStringExtra("keypromo");
        String screenshot_1 = extras.getStringExtra("keyscreenshot_1");
        String screenshot_2 = extras.getStringExtra("keyscreenshot_2");
        String screenshot_3 = extras.getStringExtra("keyscreenshot_3");
        String description = extras.getStringExtra("keydescription");
        String developer = extras.getStringExtra("keydeveloper");

        // Asign Views
        ImageView promoimg= (ImageView) findViewById(R.id.promo);
        TextView txt2 = (TextView) findViewById(R.id.tvdescription);
        TextView developertv = (TextView) findViewById(R.id.tvDeveloper);

        // Set text & image Views
        collapsingToolbar.setTitle(title);
        new ImageLoadTaskPromo(promo, promoimg).execute();
        txt2.setText(description);
        developertv.setText(developer);

        // Scroll view with screenshots
        LinearLayout screenshotLayout = (LinearLayout)findViewById(R.id.LinearLayoutScreenshots);

        for (int i=0; i<3;i++){


                LinearLayout linear = new LinearLayout(this);

                int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());

                LinearLayout.LayoutParams params
                        = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                params.rightMargin = margin;

                ScreenshotimageView[i] = new ImageView(this);
                ScreenshotimageView[i].setBackgroundColor(getResources().getColor(R.color.accent));

                linear.setLayoutParams(params);
                linear.addView(ScreenshotimageView[i]);
                screenshotLayout.addView(linear);


        }

        new ImageLoadTask(this, screenshot_1, ScreenshotimageView[0]).execute();
        new ImageLoadTask(this, screenshot_2, ScreenshotimageView[1]).execute();
        new ImageLoadTask(this, screenshot_3, ScreenshotimageView[2]).execute();



        // Install button
        Button installbutton;
        installbutton = (Button) findViewById(R.id.button);

        installbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                         Intent installtheme = new Intent(Intent.ACTION_VIEW, Uri.parse(link));

                         Bundle bndlanimation =
                                 ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                         startActivity(installtheme, bndlanimation);

            }
        }); // End install button

        // Google Plus button
        Button googleplusbutton;
        googleplusbutton = (Button) findViewById(R.id.button2);

        googleplusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent googleplustheme = new Intent(Intent.ACTION_VIEW, Uri.parse(googleplus));

                Bundle bndlanimation =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                startActivity(googleplustheme, bndlanimation);

            }
        }); // End Google Plus button

    } // End onCreate

}

