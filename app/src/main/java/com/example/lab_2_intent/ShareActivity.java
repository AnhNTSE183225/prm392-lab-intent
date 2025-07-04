package com.example.lab_2_intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShareActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        
        TextView textView = findViewById(R.id.shareTextView);
        
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    textView.setText("Received shared text:\n\n" + sharedText);
                } else {
                    textView.setText("No text content received");
                }
            }
        } else {
            textView.setText("This activity handles ACTION_SEND intents with text/plain type");
        }
    }
}
