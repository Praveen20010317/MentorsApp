package com.example.studentdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Main3Activity extends AppCompatActivity {
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;
    Button save , clear;
    EditText stud_name,stud_mob,stud_mail,father_mob,mother_mob,mentor_name,roll_no;
    String stu_name,roll,mob,mail,fat_mob,mot_mob,ment_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);

        mStorageReference = FirebaseStorage.getInstance().getReference(MainActivity.STORAGE_PATH_UPLOADS);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(MainActivity.DATABASE_PATH_UPLOADS);

        stud_name = findViewById(R.id.student_name);
        stud_mob = findViewById(R.id.mob_no);
        stud_mail = findViewById(R.id.mail_id);
        father_mob = findViewById(R.id.father_mob);
        mother_mob = findViewById(R.id.mother_mob);
        mentor_name = findViewById(R.id.mentor_name);
        roll_no = findViewById(R.id.roll_No);
        save = findViewById(R.id.save);
        clear = findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stud_name.setText(null);
                stud_mob.setText(null);
                stud_mail.setText(null);
                father_mob.setText(null);
                mother_mob.setText(null);
                mentor_name.setText(null);
                roll_no.setText(null);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stu_name = stud_name.getText().toString();
                roll = roll_no.getText().toString();
                mob = stud_mob.getText().toString();
                mail = stud_mail.getText().toString();
                fat_mob = father_mob.getText().toString();
                mot_mob = mother_mob.getText().toString();
                ment_name = mentor_name.getText().toString();

                if (stu_name.isEmpty()) {
                    stud_name.setError("Provide Student name!!");
                    stud_name.requestFocus();
                }
                 else if(roll.isEmpty()){
                    roll_no.setError("Provide Roll No!!");
                    roll_no.requestFocus();
                }
                else if(mob.isEmpty()){
                    stud_mob.setError("Provide Mobile No!!");
                    stud_mob.requestFocus();
                }
                else if(mail.isEmpty()){
                    stud_mail.setError("Provide E-mail Id!!");
                    stud_mail.requestFocus();
                }
                else if(ment_name.isEmpty()){
                    mentor_name.setError("Provide Mentor Name!!");
                    mentor_name.requestFocus();
                }

                else if (!(stu_name.isEmpty() && roll.isEmpty() && mail.isEmpty() && ment_name.isEmpty())){
                    Upload upload = new Upload(stud_name.getText().toString(), stud_mob.getText().toString()
                            , stud_mail.getText().toString(), father_mob.getText().toString(), mother_mob.getText().toString(),
                            mentor_name.getText().toString(),roll_no.getText().toString());

                    mDatabaseReference.push().setValue(upload);

                    Toast.makeText(getApplicationContext(), "Uploaded Sucessful "+stud_name .getText()+"!", Toast.LENGTH_LONG).show();
                    stud_name.setText(null);
                    stud_mob.setText(null);
                    stud_mail.setText(null);
                    father_mob.setText(null);
                    mother_mob.setText(null);
                    mentor_name.setText(null);
                    roll_no.setText(null);
                }

            }

        });
    }
}
