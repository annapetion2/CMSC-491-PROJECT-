package com.example.anonymousgradingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button rosterButton; //declare button to upload csv roster file
    private Button classButton; //declare button to start entering new course name
    private EditText className; //declare edittext to type course name into
    private TextView rosterName; //declare textview for roster file name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize button and edittext to xml design
        rosterButton = (Button) findViewById(R.id.button);
        classButton = (Button) findViewById(R.id.button2);
        className = (EditText) findViewById(R.id.editTextText);
        rosterName = (TextView) findViewById(R.id.textView);

        rosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When the user clicks the roster button, have prompt to upload roster via CSV file
                //ACTION_GET_CONTENT prompts file upload from the user
                Intent rosterIntent = new Intent(Intent.ACTION_GET_CONTENT);
                //Adds a category to our intent that makes it only accept files "CATEGORY_OPENABLE"
                rosterIntent.addCategory(Intent.CATEGORY_OPENABLE);
                //Set the type of file opened to a .csv file
                //rosterIntent.setType("text/csv");
                rosterIntent.setType("*/*");
                String[] mimetypes = {"text/csv", "text/comma-separated-values", "application/csv"};
                rosterIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                activityResultLauncher.launch(Intent.createChooser(rosterIntent, "Open CSV"));
                //activityResultLauncher.launch(rosterIntent);
            }
        });

        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                        @Override
                        //ActivityResult o is a passed activity result
                        public void onActivityResult(ActivityResult o) {
                            if (o.getResultCode() == RESULT_OK && o.getData() != null) {

                                Intent data = o.getData(); //retrieve the data(intent) from  result
                                Uri uriData = data.getData();
                                if(uriData != null) {
                                    Cursor returnCursor =
                                            getContentResolver().query(uriData,
                                                    null, null, null,
                                                    null);
                                    int nameIndex = returnCursor.getColumnIndex(
                                            OpenableColumns.DISPLAY_NAME);
                                    returnCursor.moveToFirst();
                                    rosterName.setText(returnCursor.getString(nameIndex));
                                    Log.d("MainActivity", returnCursor.getString(nameIndex));
                                }else {
                                    rosterName.setText("Import failed");
                                }
                            }else{
                                Log.d("MainActivity", "ResultCode != RESULT_OK");
                            }
                        }
                    });
}
