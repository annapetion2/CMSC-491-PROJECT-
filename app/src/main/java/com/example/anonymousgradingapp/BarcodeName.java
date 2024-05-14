package com.example.anonymousgradingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.Settings;
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

import java.util.ArrayList;

public class BarcodeName extends AppCompatActivity implements View.OnClickListener{

    private Button _return;
    private Button bt_generate;
    private Button show_barcodes;
    private ImageView iv_qr;
    private int course_pos;
    private int exam_pos;
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
                Intent myIntent = new Intent( BarcodeName.this,CourseListAddExam.class);
                classResult.launch(myIntent); // select a class to display barcodes
            }
        });
        // generate barcode on user click
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

                                Intent examIntent = new Intent(BarcodeName.this,
                                        ExamListDisplay.class);
                                examResult.launch(examIntent);
                            }
                        }
                    });
    ActivityResultLauncher<Intent> listResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        //do something
                    }
                }
            });
    ActivityResultLauncher<Intent> classResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        int index = o.getData().getIntExtra("Key", 0);
                        course_pos = index;
                        GlobalVariable.pos = index;
                        Intent examIntent = new Intent(BarcodeName.this,
                                ExamListDisplay.class);
                        examResult2.launch(examIntent); //select exam to display barcodes
                    }
                }
            });
    ActivityResultLauncher<Intent> examResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK) {
                        int index = o.getData().getIntExtra("Key2", 0);
                        exam_pos = index;
                        GlobalVariable.epos = index;

                        ArrayList<Bitmap> barcodes = new ArrayList<Bitmap>();
                        for (int i = 0;
                             i < GlobalVariable.courseList.get(course_pos).studentList.size();
                             i++) {
                            String ID = GlobalVariable.courseList.get(course_pos)
                                    .studentList.get(i).ID;
                            Bitmap bitmap = generateQR(ID + " " + course_pos + " " + exam_pos);

                            //need to fix edge case, where if you generate barcodes for an exam
                            //that is sequentially further than other exams, if you go back and
                            //generate the lower indices, it will overwrite the higher indices.
                            barcodes.add(bitmap);
                            if(GlobalVariable.courseList.get(course_pos).barcodes.size() > exam_pos){
                                GlobalVariable.courseList.get(course_pos).barcodes.add(exam_pos,barcodes);
                            }else{
                                int count = 0;
                                while(count < exam_pos){
                                        if (GlobalVariable.courseList.get(course_pos).
                                                barcodes.size() <= count){
                                            GlobalVariable.courseList.get(course_pos).barcodes.add(barcodes);
                                        }
                                    count++;
                                }
                                GlobalVariable.courseList.get(course_pos).barcodes.add(barcodes);
                            }
                            //GlobalVariable.courseList.get(course_pos).studentList
                                    //.get(i).barcode_id = bitmap;
                        }
                    }
                }
            });
    ActivityResultLauncher<Intent> examResult2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        int index = o.getData().getIntExtra("Key2", 0);
                        exam_pos = index;
                        GlobalVariable.epos = index;
                        Intent myIntent = new Intent(BarcodeName.this, StudentListAddBarcode.class);
                        myIntent.putExtra("key",exam_pos);
                        listResult.launch(myIntent); //display barcodes
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
                //iv_qr.setImageBitmap(bitmap);
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