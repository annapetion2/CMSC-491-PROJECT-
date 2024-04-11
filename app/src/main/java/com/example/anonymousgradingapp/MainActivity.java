package com.example.anonymousgradingapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/*Need to implement View.OnClickListener because the program cannot utilize ActivityResultLauncher
for the simple task of displaying text from the edittext to the textview, without the use of
an intent that mandates a source and a destination. Thus we simply override OnClick without
the result launcher to avoid the need of an intent.
*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button rosterButton; //declare button to upload csv roster file
    private Button classButton; //declare button to start entering new course name
    private EditText className; //declare edittext to type course name into
    private TextView rosterName; //declare textview for roster file name
    private TextView classDisplay; //declare textview for new course name
    private EditText instructorName;
    private Button instructorButton;
    private TextView instructorDisplay;
    private Button back_button;
    // UI components for adding courses
    private Button addButton;
    private ArrayList<String> courseNames = new ArrayList<>(); // Holds course names for simplicity
    private ListView coursesListView; // Displays the list of courses
    private ArrayAdapter<String> adapter; // Adapter for the ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize buttons, edittext, and textview
        rosterButton = (Button) findViewById(R.id.button);
        classButton = (Button) findViewById(R.id.button2);
        back_button = (Button) findViewById(R.id.back_to_master);
        className = (EditText) findViewById(R.id.editTextText);
        classDisplay = (TextView) findViewById(R.id.textView2);
        rosterName = (TextView) findViewById(R.id.textView);

        instructorName = (EditText) findViewById(R.id.editTextText2);
        instructorButton = (Button) findViewById(R.id.button4);
        instructorDisplay = (TextView) findViewById(R.id.textView5);

        classButton.setOnClickListener(this); //have the activity listen to the add course button
        instructorButton.setOnClickListener(this); //have activity listen to add instructor button
        back_button.setOnClickListener(this); //have activity listen to back button

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

        /*
        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent courseIntent = new Intent();
                String courseName = className.getText().toString();
                courseIntent.putExtra("key1", courseName);
                activityResultLauncher2.launch(courseIntent);
            }
        });
        */
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
                        //retrieve the uri from the intent's data
                        Uri uriData = data.getData();
                        //check if uri is empty
                        if (uriData != null) {
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
                            rosterName.setText("Imported: " + returnCursor.getString(nameIndex));
                            //Log.d("MainActivity", returnCursor.getString(nameIndex));
                        } else {
                            //if the uri is null then the file was empty or did not import
                            rosterName.setText("Import failed");
                        }
                    } else {
                        //The result code was not valid, or the return intent was empty
                        Log.d("MainActivity", "ResultCode != RESULT_OK");
                    }
                }
            });

    /*
    ActivityResultLauncher<Intent> activityResultLauncher2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    //validity check for returned file from user
                    if (o.getResultCode() == RESULT_OK && o.getData() != null) {
                        //retrieve the string data
                        Intent data = o.getData();
                        String text = data.getStringExtra("key1");
                        classDisplay.setText("Course added: " + text);
                        finish();
                    }
                }
            });
    */

    @Override
    public void onClick(View v) {
        //if the id of the clicked button is the desired add course button
        if (v.getId() == R.id.button2) {
            //extract the text data from the edittext
            String courseName = className.getText().toString();
            //check if the edittext is empty
            if (courseName != "") {
                //set the textview to the text from the edittext, which displays user's added course
                classDisplay.setText("Added course: " + courseName);
                //create a new course with the name initialized to the user's input
                Course newCourse = new Course(courseName);
                //add the new course to the list of all course in the process
                GlobalVariable.courseList.add(newCourse);

                /*
                // save the data to sharedpreference
                SharedPreferences prefs = getSharedPreferences("Courses", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                // converting object to json
                Gson gson = new Gson();
                // converting the courses in the arrayList to a json
                String json = gson.toJson(courseList);
                //put the Json string into the sharedPreferences
                editor.putString("courseList", json);
                //commiting the changes to shared preferences
                editor.apply();
                */

            }
        }else if (v.getId() == R.id.button4){ //check if button press was to add new instructor
            String teacherName = instructorName.getText().toString(); //extract name from edittext
            if(teacherName != ""){ //check if string is empty
                //display the instructor's name in the textview
                instructorDisplay.setText("Added instructor: " + teacherName);
            }
        }else if (v.getId() == R.id.back_to_master){ //check if button press was the back button
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }

    /*
    private void saveCourses() {
        // Save the current list of course names to SharedPreferences
        SharedPreferences prefs = getSharedPreferences("Courses", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(courseNames); // Convert the list to JSON
        editor.putString("courseList", json); // Save under "courseList" key
        editor.apply();
    }

    private void loadCourses() {
        // Load the list of course names from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("Courses", MODE_PRIVATE);
        String json = prefs.getString("courseList", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> loadedCourses = gson.fromJson(json, type);
        if (loadedCourses != null) {
            courseNames.clear();
            courseNames.addAll(loadedCourses);
            adapter.notifyDataSetChanged(); // Refresh the ListView with the loaded courses
        }
    }
    */
}


