package com.example.navscrreens;

public class MedRec {
    String Date;
    String Illness;
    String Practitioner;
    String PrescribedMedicine;
    String StudentEmail;

    public MedRec(){}

    public MedRec(String date, String illness, String practitioner, String prescribedMed, String studEmail) {
        Date = date;
        Illness = illness;
        Practitioner = practitioner;
        PrescribedMedicine = prescribedMed;
        StudentEmail = studEmail;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getIllness() {
        return Illness;
    }

    public void setIllness(String illness) {
        Illness = illness;
    }

    public String getPractitioner() {
        return Practitioner;
    }

    public void setPractitioner(String practitioner) {
        Practitioner = practitioner;
    }

    public String getPrescribedMed() {
        return PrescribedMedicine;
    }

    public void setPrescribedMed(String prescribedMed) {
        PrescribedMedicine = prescribedMed;
    }

    public String getStudEmail() {
        return StudentEmail;
    }

    public void setStudEmail(String studEmail) {
        StudentEmail = studEmail;
    }
}