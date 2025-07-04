package com.example.lab_2_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        
        // Get intent data and display it
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String inputString = extras.getString("yourkey");
            TextView view = (TextView) findViewById(R.id.displayintentextra);
            view.setText("Received: " + inputString);
        }
    }

    public void onFinishClick(View view) {
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.returnValue);
        String string = editText.getText().toString();
        intent.putExtra("returnkey", string);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
