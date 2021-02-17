package com.example.studentdetails;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String STORAGE_PATH_UPLOADS;
    public static String DATABASE_PATH_UPLOADS;

    EditText ac_first_year, ac_end_year;
    CheckBox cse,ece,civil,mech,s1,s2,s3,s4,s5,s6,s7,s8,sec_A,sec_B,sec_C;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ac_first_year = findViewById(R.id.ac_start);
        ac_end_year = findViewById(R.id.ac_end);

        sec_A=findViewById(R.id.check_A);
        sec_B=findViewById(R.id.check_B);
        sec_C=findViewById(R.id.check_C);
        cse=findViewById(R.id.check_cse);
        ece=findViewById(R.id.check_ece);
        mech=findViewById(R.id.check_mech);
        civil=findViewById(R.id.check_civil);

        next=findViewById(R.id.next);


        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {

                if (ac_first_year.length() != 4 ){
                    ac_first_year.setError("Enter Correctly");
                    ac_first_year.requestFocus();
                }
                else if (ac_end_year.length() != 4) {
                    ac_end_year.setError("Enter Correctly");
                    ac_end_year.requestFocus();
                }

                    int count = 0;

                    if (cse.isChecked() && sec_A.isChecked()) {
                        STORAGE_PATH_UPLOADS = "cse_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "cse_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (cse.isChecked() && sec_B.isChecked()) {
                        STORAGE_PATH_UPLOADS = "cse_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "cse_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (cse.isChecked()  && sec_C.isChecked()) {
                        STORAGE_PATH_UPLOADS = "cse_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "cse_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }

                    //ECE

                    if (ece.isChecked() && sec_A.isChecked()) {
                        STORAGE_PATH_UPLOADS = "ece_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "ece_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (ece.isChecked() && sec_B.isChecked()) {
                        STORAGE_PATH_UPLOADS = "ece_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "ece_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (ece.isChecked() && sec_C.isChecked()) {
                        STORAGE_PATH_UPLOADS = "ece_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "ece_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }

                    //CIVIL

                    if (civil.isChecked()  && sec_A.isChecked()) {
                        STORAGE_PATH_UPLOADS = "civil_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "civil_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (civil.isChecked() && sec_B.isChecked()) {
                        STORAGE_PATH_UPLOADS = "civil_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "civil_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (civil.isChecked()  && sec_C.isChecked()) {
                        STORAGE_PATH_UPLOADS = "civil_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "civil_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }


                    //MECH

                    if (mech.isChecked()  && sec_A.isChecked()) {
                        STORAGE_PATH_UPLOADS = "mech_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "mech_sec_A"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (mech.isChecked() && sec_B.isChecked()) {
                        STORAGE_PATH_UPLOADS = "mech_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "mech_sec_B"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }
                    if (mech.isChecked() && sec_C.isChecked()) {
                        STORAGE_PATH_UPLOADS = "mech_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        DATABASE_PATH_UPLOADS = "mech_sec_C"+" "+ac_first_year.getText()+ac_end_year.getText();
                        count++;
                    }

                    if (ac_end_year.length() == 4 && ac_first_year.length() == 4 ) {
                        if (count == 1) {
                            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                            Network networkInfo = connectivityManager.getActiveNetwork();
                            if (networkInfo != null) {
                                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                startActivity(intent);
                            } else {
                                Intent intent1 = new Intent(getApplicationContext(), Error_connection.class);
                                startActivity(intent1);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "SELECT CORRECTLY !", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        });
    }
}

