package com.example.termwork2;

public class ContactModal {
    String contactName;
    String contactNumber;
    public String getContactName(){ return contactName;}
    public void setContactName(String studentName){
        this.contactName = studentName;
    }
    public String getContactNumber(){ return contactNumber;}
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }
    public ContactModal(String contactName,String contactNumber){
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }
}
