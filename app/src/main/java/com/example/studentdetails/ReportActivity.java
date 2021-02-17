package com.example.studentdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CallLog;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {

    TextView textView1,textView2,textView3 ,textView4= null;
    String stu_name,mob,fat_mob,mot_mob,key;
    FloatingActionButton fb_stud , fab_father , fab_mother , fab_pdf;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_report);

        relativeLayout = findViewById(R.id.layout_empty);
        textView1 = findViewById(R.id.stud_call_log);
        textView2 = findViewById(R.id.father_call_log);
        textView3 = findViewById(R.id.mother_call_log);
        textView4 = findViewById(R.id.empty_log);
        fb_stud = findViewById(R.id.fab_stud);
        fab_pdf = findViewById(R.id.fab_pdf);
        fab_father = findViewById(R.id.fab_father);
        fab_mother = findViewById(R.id.fab_mother);

        key = getIntent().getStringExtra("key");
        stu_name = getIntent().getStringExtra("studn");
        mob = getIntent().getStringExtra("mob");
        fat_mob = getIntent().getStringExtra("father_no");
        mot_mob = getIntent().getStringExtra("mother_no");

        relativeLayout.setVisibility(View.VISIBLE);

        fb_stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stud_mobile = mob;
                String mother_mob = mot_mob;
                StringBuffer sb = new StringBuffer();
                Cursor managedCursor;
                managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
                int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
                int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
                int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
                int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
                sb.append("\n Student Call History : \n");
                while (managedCursor.moveToNext()) {
                    String phNumber = managedCursor.getString(number);
                    if (PhoneNumberUtils.compare(stud_mobile, phNumber)){
                        String callType = managedCursor.getString(type);
                        String callDate = managedCursor.getString(date);
                        Date callDayTime = new Date(Long.valueOf(callDate));
                        String callDuration = managedCursor.getString(duration);
                        String dir = null;
                        int dircode = Integer.parseInt(callType);
                        switch (dircode) {
                            case CallLog.Calls.OUTGOING_TYPE:
                                dir = "OUTGOING";
                                break;

                            case CallLog.Calls.INCOMING_TYPE:
                                dir = "INCOMING";
                                break;
                            case CallLog.Calls.MISSED_TYPE:
                                dir = "MISSED";
                                break;
                        }
                        sb.append("\nPHONE NUMBER :- ").append(phNumber).append("\n CALL TYPE :- ").append(dir).append("\n CALL DATE :- ").append(callDayTime).append("\n CALL DURATION :- ").append(callDuration);
                        sb.append("\n---------------------------------------");
                        textView1.setText(sb);
                    }
                }
                if (textView1.getText().toString().isEmpty()){
                    textView4.setText("\n STUDENT - No Call History");
                }
                emptyLayout();
            }
        });

        fab_father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String father_mob = fat_mob;
                StringBuffer sb1 = new StringBuffer();
                Cursor managedCursor1;
                managedCursor1 = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
                int number1 = managedCursor1.getColumnIndex(CallLog.Calls.NUMBER);
                int type1 = managedCursor1.getColumnIndex(CallLog.Calls.TYPE);
                int date1 = managedCursor1.getColumnIndex(CallLog.Calls.DATE);
                int duration1 = managedCursor1.getColumnIndex(CallLog.Calls.DURATION);
                sb1.append("\n Father Call History : \n");
                while (managedCursor1.moveToNext()) {
                    String phNumber = managedCursor1.getString(number1);
                    if (PhoneNumberUtils.compare(father_mob, phNumber)){
                        String callType = managedCursor1.getString(type1);
                        String callDate = managedCursor1.getString(date1);
                        Date callDayTime = new Date(Long.valueOf(callDate));
                        String callDuration = managedCursor1.getString(duration1);
                        String dir = null;
                        int dircode = Integer.parseInt(callType);
                        switch (dircode) {
                            case CallLog.Calls.OUTGOING_TYPE:
                                dir = "OUTGOING";
                                break;

                            case CallLog.Calls.INCOMING_TYPE:
                                dir = "INCOMING";
                                break;
                            case CallLog.Calls.MISSED_TYPE:
                                dir = "MISSED";
                                break;
                        }
                        sb1.append("\nPHONE NUMBER :- ").append(phNumber).append("\n Call Type :- ").append(dir).append("\n Call Date :- ").append(callDayTime).append("\n Call Duration :- ").append(callDuration);
                        sb1.append("\n---------------------------------------");
                        textView2.setText(sb1);
                    }

                }
                if (textView2.getText().toString().isEmpty()){
                    textView4.setText("\nFATHER - No Call History");
                }
                emptyLayout();

            }
        });
        fab_mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mother_mob = mot_mob;
                StringBuffer sb2 = new StringBuffer();
                Cursor managedCursor2;
                managedCursor2 = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
                int number2 = managedCursor2.getColumnIndex(CallLog.Calls.NUMBER);
                int type2 = managedCursor2.getColumnIndex(CallLog.Calls.TYPE);
                int date2 = managedCursor2.getColumnIndex(CallLog.Calls.DATE);
                int duration2 = managedCursor2.getColumnIndex(CallLog.Calls.DURATION);
                sb2.append("\n Mother Call History : \n");
                while (managedCursor2.moveToNext()) {
                    String phNumber = managedCursor2.getString(number2);
                    if (PhoneNumberUtils.compare(mother_mob, phNumber)){
                        String callType = managedCursor2.getString(type2);
                        String callDate2 = managedCursor2.getString(date2);
                        Date callDayTime = new Date(Long.valueOf(callDate2));
                        String callDuration = managedCursor2.getString(duration2);
                        String dir = null;
                        int dircode = Integer.parseInt(callType);
                        switch (dircode) {
                            case CallLog.Calls.OUTGOING_TYPE:
                                dir = "OUTGOING";
                                break;

                            case CallLog.Calls.INCOMING_TYPE:
                                dir = "INCOMING";
                                break;
                            case CallLog.Calls.MISSED_TYPE:
                                dir = "MISSED";
                                break;
                        }
                        sb2.append("\nPHONE NUMBER :- ").append(phNumber).append("\n Call Type :- ").append(dir).append("\n Call Date :- ").append(callDayTime).append("\n Call Duration :- ").append(callDuration);
                        sb2.append("\n---------------------------------------");
                        textView3.setText(sb2);
                    }
                }
                if (textView3.getText().toString().isEmpty()){
                    textView4.setText("\n MOTHER - No Call History");
                }
                emptyLayout();
            }
        });
        fab_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ReportActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    createpdf(textView1.getText().toString() , textView2.getText().toString() ,textView3.getText().toString());
                }
                else {
                    ActivityCompat.requestPermissions(ReportActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 9);
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==9&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
            createpdf(textView1.getText().toString() , textView2.getText().toString() ,textView3.getText().toString());
        }
        else {
            Toast.makeText(ReportActivity.this,"Please provide permission to store pdf file...",Toast.LENGTH_LONG).show();
        }
    }

    private void createpdf(String tv1_pdf , String tv2_pdf , String tv3_pdf) {
        Document document = new Document();
        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Student Details";
        File dir = new File(folder);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File filepath = new File(dir,"/" + stu_name + ".pdf");
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filepath));
                document.open();
                document.add(new Paragraph(tv1_pdf));
                document.add(new Paragraph(tv2_pdf));
                document.add(new Paragraph(tv3_pdf));
                document.close();
                Toast.makeText(getApplicationContext(),  stu_name+dir+"/" , Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }

    }

    private void emptyLayout() {
        if (textView1.getText().toString().isEmpty() || textView2.getText().toString().isEmpty() || textView3.getText().toString().isEmpty())
        {
            relativeLayout.setVisibility(View.GONE);
        }
    }
}
