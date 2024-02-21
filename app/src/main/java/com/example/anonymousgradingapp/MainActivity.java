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

        //initialize buttons, edittext, and textview
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
                //Allow the intent to take in any file in the system denoted by "*/*"
                rosterIntent.setType("*/*");
                //Define a string array for the intent to take in csv files, this is done after
                // allowing all files types = "*/*" because trying to select a .csv file with an
                // initial intent declaration of .setType("text/csv") resulted in greyed-out .csv
                // files in the user's file explorer
                String[] mimetypes = {"text/csv", "text/comma-separated-values", "application/csv"};
                //Add the array of mime_types to our intent, which allow it to successfully return
                //the listed types of files, via putExtra()
                rosterIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                //Launch the activityresultlauncher with our intent
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

    //ActivityResultLauncher for the roster file upload
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                        @Override
                        //ActivityResult o is a passed activity result
                        public void onActivityResult(ActivityResult o) {
                            //validity check for returned file from user
                            if (o.getResultCode() == RESULT_OK && o.getData() != null) {
                                //retrieve the data(intent) from  result
                                Intent data = o.getData();
                                //retrieve the uri from the intent data
                                Uri uriData = data.getData();
                                //check if uri is empty
                                if(uriData != null) {
                                    //Define cursor to extract name from the user's file
                                    Cursor returnCursor =
                                            getContentResolver().query(uriData,
                                                    null, null, null,
                                                    null);
                                    //Define the index where the file's name is located
                                    int nameIndex = returnCursor.getColumnIndex(
                                            OpenableColumns.DISPLAY_NAME);
                                    //Move back to the first row
                                    returnCursor.moveToFirst();
                                    //Set our textview to the file name by indexing the cursor
                                    rosterName.setText(returnCursor.getString(nameIndex) + '\n' +
                                            "Imported");
                                    //Log.d("MainActivity", returnCursor.getString(nameIndex));
                                }else{
                                    //if the uri is null then the file was empty or did not import
                                    rosterName.setText("Import failed");
                                }
                            }else{
                                //The result code was not valid, or the return intent was empty
                                Log.d("MainActivity", "ResultCode != RESULT_OK");
                            }
                        }
                    });
}
