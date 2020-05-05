package com.example.myassisstent.model;

public class Feedback {
    String Feedback_ID;
    String Emp_ID;
    String Feedback_Text;
    String Admin_Email;

    public Feedback()
    {

    }
    public String getFeedback_ID() {
        return Feedback_ID;
    }

    public void setFeedback_ID(String feedback_ID) {
        Feedback_ID = feedback_ID;
    }

    public String getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(String emp_ID) {
        Emp_ID = emp_ID;
    }

    public String getFeedback_Text() {
        return Feedback_Text;
    }

    public void setFeedback_Text(String feedback_Text) {
        Feedback_Text = feedback_Text;
    }

    public String getAdmin_Email() {
        return Admin_Email;
    }

    public void setAdmin_Email(String admin_Email) {
        Admin_Email = admin_Email;
    }

}
