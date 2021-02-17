package com.example.studentdetails;

import java.io.Serializable;

public class Upload implements Serializable {

    public String student_name;
    public String student_mob;
    public String student_mail;
    public String father_mob;
    public String mother_mob;
    public String mentor_name;
    public String rollno;
    public String key;

    public Upload(){

    }

    public Upload(String student_name, String student_mob, String student_mail, String father_mob, String mother_mob, String mentor_name,String rollno) {
        this.student_name = student_name;
        this.student_mob = student_mob;
        this.student_mail = student_mail;
        this.father_mob = father_mob;
        this.mother_mob = mother_mob;
        this.mentor_name = mentor_name;
        this.rollno = rollno;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_mob() {
        return student_mob;
    }

    public void setStudent_mob(String student_mob) {
        this.student_mob = student_mob;
    }

    public String getStudent_mail() {
        return student_mail;
    }

    public void setStudent_mail(String student_mail) {
        this.student_mail = student_mail;
    }

    public String getFather_mob() {
        return father_mob;
    }

    public void setFather_mob(String father_mob) {
        this.father_mob = father_mob;
    }

    public String getMother_mob() {
        return mother_mob;
    }

    public void setMother_mob(String mother_mob) {
        this.mother_mob = mother_mob;
    }

    public String getMentor_name() {
        return mentor_name;
    }

    public void setMentor_name(String mentor_name) {
        this.mentor_name = mentor_name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
