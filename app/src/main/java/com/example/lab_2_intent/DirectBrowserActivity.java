package com.example.lab_2_intent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DirectBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        // Display a simple message
        TextView textView = findViewById(R.id.browserTextView);
        
        try {
            // Launch our own BrowserActivity directly
            String url = "http://www.vogella.com";
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setComponent(new ComponentName(getPackageName(), 
                                                getPackageName() + ".BrowserActivity"));
            startActivity(intent);
            
            // Since we're launching another activity, we'll finish this one
            finish();
        } catch (Exception e) {
            textView.setText("Error launching browser: " + e.getMessage());
        }
    }
}
