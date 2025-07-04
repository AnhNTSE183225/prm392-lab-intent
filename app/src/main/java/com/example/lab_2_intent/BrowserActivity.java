package com.example.lab_2_intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class BrowserActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // To keep this example simple, we allow network access
        // in the user interface thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        setContentView(R.layout.activity_browser);
        
        Intent intent = getIntent();
        TextView text = (TextView) findViewById(R.id.browserTextView);
        
        // Display welcome message first
        text.setText("🌟 Welcome to the Sketch Browser 🌟\n\n");
        
        // To get the action of the intent use
        String action = intent.getAction();
        
        if (action == null || !action.equals(Intent.ACTION_VIEW)) {
            text.append("This browser activity handles ACTION_VIEW intents.\n\nNo valid URL found.");
            return;
        }
        
        // To get the data use
        Uri data = intent.getData();
        if (data == null) {
            text.append("No URL data found in the intent.");
            return;
        }
        
        text.append("Loading URL: " + data.toString() + "\n\n");
        
        URL url;
        try {
            // Handle URLs with query parameters and fragments
            String urlPath = data.getPath();
            if (urlPath == null) urlPath = "";
            
            String query = data.getQuery();
            if (query != null) urlPath += "?" + query;
            
            String fragment = data.getFragment();
            if (fragment != null) urlPath += "#" + fragment;
            
            url = new URL(data.getScheme(), data.getHost(), data.getPort(), urlPath);
            text.append("Connecting to " + url.getHost() + "...\n\n");
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            int lineCount = 0;
            while ((line = rd.readLine()) != null && lineCount < 100) {
                text.append(line + "\n");
                lineCount++;
            }
            
            if (lineCount >= 100) {
                text.append("\n\n... [Content truncated for display] ...");
            }
            
            text.append("\n\n🎉 Page loaded successfully!");
            
        } catch (Exception e) {
            text.append("⚠️ Error loading page: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
