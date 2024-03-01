package com.example.anonymousgradingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener{
    private Button scan_button;
    private Button back_button;
    private ImageView examView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        scan_button = (Button)findViewById(R.id.scan_button);
        back_button = (Button)findViewById(R.id.back_to_master3);
        examView = (ImageView)findViewById(R.id.imageView);

        back_button.setOnClickListener(this);

        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(cameraIntent);
            }
        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                Intent data = o.getData();
                                Bitmap image = (Bitmap) data.getExtras().get("data");
                                examView.setImageBitmap(image);
                            }
                        }
                    });

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back_to_master3){
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }
}