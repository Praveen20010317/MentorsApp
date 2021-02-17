package com.example.studentdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;


public class StudentDetails extends AppCompatActivity {
    FloatingActionButton fab_edit,fab_delete,fab_report;
    TextView studt_name, stud_mob, stud_mail, father_mob, mother_mob, mentor_name, rollno;
    ImageView stud_whatsapp, fath_whatsapp, moth_whatsapp, mail, stud_call, fath_call, mother_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_details);

        studt_name = findViewById(R.id.stud_name);
        stud_mob = findViewById(R.id.mobi_no);
        stud_mail = findViewById(R.id.email_id);
        father_mob = findViewById(R.id.fath_mob);
        mother_mob = findViewById(R.id.moth_mob);
        mentor_name = findViewById(R.id.ment_name);
        rollno = findViewById(R.id.roll_no);
        stud_whatsapp = findViewById(R.id.whats_app);
        fath_whatsapp = findViewById(R.id.whats_app_fat);
        moth_whatsapp = findViewById(R.id.whats_app_mot);
        mail = findViewById(R.id.send_mail);
        stud_call = findViewById(R.id.stud_cal);
        fath_call = findViewById(R.id.father_cal);
        mother_call = findViewById(R.id.mother_cal);
        fab_edit= findViewById(R.id.fab_edit);
        fab_delete = findViewById(R.id.fab_delete);
        fab_report = findViewById(R.id.fab_report);

        final Upload upload = (Upload) getIntent().getSerializableExtra("name");

        studt_name.setText(upload.getStudent_name());
        stud_mob.setText(upload.getStudent_mob());
        stud_mail.setText(upload.getStudent_mail());
        father_mob.setText(upload.getFather_mob());
        mother_mob.setText(upload.getMother_mob());
        mentor_name.setText(upload.getMentor_name());
        rollno.setText(upload.getRollno());

        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = upload.getKey();
                Intent intent = new Intent(StudentDetails.this,Update_activity.class);
                intent.putExtra("key" ,key);
                intent.putExtra("studn",studt_name.getText().toString());
                intent.putExtra("rollno",rollno.getText().toString());
                intent.putExtra("mob",stud_mob.getText().toString());
                intent.putExtra("email",stud_mail.getText().toString());
                intent.putExtra("father_no",father_mob.getText().toString());
                intent.putExtra("mother_no",mother_mob.getText().toString());
                intent.putExtra("ment",mentor_name.getText().toString());
                startActivity(intent);
            }
        });

        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dailog = new AlertDialog.Builder(v.getRootView().getContext());
                dailog.setMessage("Are you wants to delete?");
                dailog.setTitle("Delete");
                dailog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String key = upload.getKey();
                        FirebaseDatabase.getInstance().getReference(MainActivity.DATABASE_PATH_UPLOADS).child(key).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {

                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        finish();
                                        //Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                        //startActivity(intent);
                                        //Toast.makeText(context,"Deleted Successfully"+upload.getStudent_name() ,Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = dailog.create();
                alertDialog.show();
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{stud_mail.getText().toString()});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                //i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stud_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + stud_mob.getText() + "&text="));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        fath_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + father_mob.getText() + "&text="));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        moth_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + mother_mob.getText() + "&text="));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        stud_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(Intent.ACTION_DIAL);
                cIntent.setData(Uri.parse("tel:" + stud_mob.getText()));
                startActivity(cIntent);
            }
        });
        fath_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(Intent.ACTION_DIAL);
                cIntent.setData(Uri.parse("tel:" + father_mob.getText()));
                startActivity(cIntent);
            }
        });
        mother_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(Intent.ACTION_DIAL);
                cIntent.setData(Uri.parse("tel:" + mother_mob.getText()));
                startActivity(cIntent);
            }
        });

        fab_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(StudentDetails.this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                    student_call_history();
                }
                else {
                    ActivityCompat.requestPermissions(StudentDetails.this, new String[]{Manifest.permission.READ_CALL_LOG}, 9);
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==9&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
            student_call_history();
        }
        else {
            Toast.makeText(StudentDetails.this,"Please provide permission to read call history",Toast.LENGTH_LONG).show();
        }
    }

    private void student_call_history() {
        final Upload upload = (Upload) getIntent().getSerializableExtra("name");
        final String key = upload.getKey();
        Intent intent = new Intent(StudentDetails.this,ReportActivity.class);
        intent.putExtra("key" ,key);
        intent.putExtra("studn",studt_name.getText().toString());
        intent.putExtra("mob",stud_mob.getText().toString());
        intent.putExtra("father_no",father_mob.getText().toString());
        intent.putExtra("mother_no",mother_mob.getText().toString());
        startActivity(intent);
    }


}
