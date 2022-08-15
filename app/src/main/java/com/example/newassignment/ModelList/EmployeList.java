package com.example.newassignment.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class EmployeList
{
    String id;
    String employee_name;
    String employee_salary;
    String employee_age;
    String profile_image;

    public EmployeList(String id, String employee_name, String employee_salary, String employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public EmployeList(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.employee_name = jsonObject.getString("employee_name");
            this.employee_salary = jsonObject.getString("employee_salary");
            this.employee_age = jsonObject.getString("employee_age");
            this.profile_image = jsonObject.getString("profile_image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(String employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(String employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
