package com.example.studentdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Update_activity extends AppCompatActivity {

    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;
    Button update;
    EditText stud_name,stud_mob,stud_mail,father_mob,mother_mob,mentor_name,roll_no;

    String stu_name,roll,mob,mail,fat_mob,mot_mob,ment_name,key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.update_activity);

        mStorageReference = FirebaseStorage.getInstance().getReference(MainActivity.STORAGE_PATH_UPLOADS);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(MainActivity.DATABASE_PATH_UPLOADS);

        stud_name = findViewById(R.id.student_name);
        stud_mob = findViewById(R.id.mob_no);
        stud_mail = findViewById(R.id.mail_id);
        father_mob = findViewById(R.id.father_mob);
        mother_mob = findViewById(R.id.mother_mob);
        mentor_name = findViewById(R.id.mentor_name);
        roll_no = findViewById(R.id.roll_No);
        update = findViewById(R.id.update2);

        key = getIntent().getStringExtra("key");
        stu_name = getIntent().getStringExtra("studn");
        roll = getIntent().getStringExtra("rollno");
        mob = getIntent().getStringExtra("mob");
        mail = getIntent().getStringExtra("email");
        fat_mob = getIntent().getStringExtra("father_no");
        mot_mob = getIntent().getStringExtra("mother_no");
        ment_name = getIntent().getStringExtra("ment");

        stud_name.setText(stu_name);
        stud_mob.setText(mob);
        roll_no.setText(roll);
        stud_mail.setText(mail);
        father_mob.setText(fat_mob);
        mother_mob.setText(mot_mob);
        mentor_name.setText(ment_name);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                else if (!(stu_name.isEmpty() && roll.isEmpty() && mail.isEmpty() && ment_name.isEmpty())) {
                    Upload upload1 = new Upload(stud_name.getText().toString(), stud_mob.getText().toString()
                            , stud_mail.getText().toString(), father_mob.getText().toString(), mother_mob.getText().toString(),
                            mentor_name.getText().toString(), roll_no.getText().toString());

                    FirebaseDatabase.getInstance().getReference(MainActivity.DATABASE_PATH_UPLOADS).child(key).setValue(upload1)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                    startActivity(intent);

                                }
                            });
                }


                }
        });


    }
}
