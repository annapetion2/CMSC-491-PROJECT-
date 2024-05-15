package com.example.anonymousgradingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Examgrade;
import com.amplifyframework.datastore.generated.model.Todo;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Random;

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
        scan_button.setOnClickListener(v->{
            ScanCode();
        });

        /*scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(cameraIntent);
            }
        });*/
    }
    private void ScanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt(" Volume up to flash on ");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult( new ScanContract(), result->{
        // check if something is return after the scanner
        if(result.getContents()!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity4.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());

            String[] splitted = result.getContents().split("\\s+"); //split the barcode value into 2

            int course_position = 0;
            course_position = Integer.parseInt(splitted[1]); //convert 2nd part of barcode string to course pos

            int exam_position = 0;
            exam_position = Integer.parseInt(splitted[2]); //convert 3nd part of barcode string to exam pos

            GlobalVariable.scanned_course.add(course_position); //add course #
            GlobalVariable.scanned_exam.add(exam_position); //add exam #
            GlobalVariable.scanned_ID.add(splitted[0]); //add ID

            final int random = new Random().nextInt(101);

            Examgrade grade = Examgrade.builder()
                    .course(GlobalVariable.courseList.get(course_position).getName())
                    .exam(GlobalVariable.courseList.get(course_position).exams.get(exam_position).getName())
                    .grade(random)
                    .studentid(splitted[0])
                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(grade),
                    response -> Log.i("GraphQL", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("GraphQL", "Create failed", error)
            );

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }

    });

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