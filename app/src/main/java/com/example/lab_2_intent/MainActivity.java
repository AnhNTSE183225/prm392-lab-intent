package com.example.lab_2_intent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // constant to determine which sub-activity returns
    private static final int REQUEST_CODE = 10;
    private static final int REQUEST_IMAGE_PICK = 1;
    
    private Bitmap bitmap;
    private ImageView imageView;
    
    // Activity result launcher for image picking
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        imageView = findViewById(R.id.selectedImage);
        
        // Initialize image picker launcher
        imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        handleImagePickResult(result.getData());
                    }
                }
            }
        );
    }

    public void onClick(View view) {
        EditText text = (EditText) findViewById(R.id.inputforintent);
        String value = text.getText().toString();
        
        // Create intent to start ResultActivity
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("yourkey", value);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void shareData(View view) {
        EditText text = (EditText) findViewById(R.id.inputforintent);
        String textToShare = text.getText().toString();
        
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    
    public void openBrowser(View view) {
        // Simple direct approach for Exercise 8
        Intent directIntent = new Intent(this, BrowserActivity.class);
        directIntent.setAction(Intent.ACTION_VIEW);
        directIntent.setData(Uri.parse("http://www.vogella.com"));
        
        try {
            startActivity(directIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Cannot open browser: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    public void pickImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        imagePickerLauncher.launch(intent);
    }
    
    public void showImplicitIntents(View view) {
        Intent intent = new Intent(this, ImplicitIntentsActivity.class);
        startActivity(intent);
    }
    
    private void handleImagePickResult(Intent data) {
        InputStream stream = null;
        try {
            // recycle unused bitmaps
            if (bitmap != null) {
                bitmap.recycle();
            }
            stream = getContentResolver().openInputStream(data.getData());
            bitmap = BitmapFactory.decodeStream(stream);
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Image selected successfully!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null && data.hasExtra("returnkey")) {
                String result = Objects.requireNonNull(data.getExtras()).getString("returnkey");
                if (result != null && !result.isEmpty()) {
                    Toast.makeText(this, "Returned: " + result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}