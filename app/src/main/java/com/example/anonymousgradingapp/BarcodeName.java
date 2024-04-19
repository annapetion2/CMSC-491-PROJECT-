package com.example.anonymousgradingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BarcodeName extends AppCompatActivity implements View.OnClickListener{

    private Button _return;
    private Button bt_generate;
    private Button show_barcodes;
    private ImageView iv_qr;
    private int course_pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_name);

        _return = (Button) findViewById(R.id.back_to_master4);

        _return.setOnClickListener(this);

        show_barcodes = (Button)findViewById(R.id.showBList);
        bt_generate = (Button) findViewById(R.id.bt_Generate);
        iv_qr = (ImageView) findViewById(R.id.iv_qr);


        show_barcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // generate when the user click on it
        bt_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( BarcodeName.this,CourseListAddExam.class);
                activityResultLauncher.launch(myIntent);
            }
        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if (o.getResultCode() == RESULT_OK) {
                                int index = o.getData().getIntExtra("Key", 0);
                                course_pos = index;
                                GlobalVariable.pos = index;
                                for (int i = 0;
                                     i < GlobalVariable.courseList.get(course_pos).studentList.size();
                                     i++) {
                                    String name = GlobalVariable.courseList.get(course_pos)
                                            .studentList.get(i).fname;
                                    Bitmap bitmap = generateQR(name);
                                    GlobalVariable.courseList.get(course_pos).studentList
                                            .get(i).barcode_id = bitmap;
                                }
                            }
                        }
                    });

        private Bitmap generateQR(String name){
            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                BitMatrix matrix = writer.encode(name, BarcodeFormat.QR_CODE, 400, 400);
                BarcodeEncoder encoder = new BarcodeEncoder();
                // Store bitmap value and return from the encoding
                Bitmap bitmap = encoder.createBitmap(matrix);
                iv_qr.setImageBitmap(bitmap);
                return bitmap;
            } catch (WriterException e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back_to_master4){
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }
}