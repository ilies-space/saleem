package com.ahmed.saleem.app;


import java.io.Serializable;

public class OrderModel implements Serializable {

    String phone;
    String model ;
    String problem ;
    String problemDiscription;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getProblemDiscription() {
        return problemDiscription;
    }

    public void setProblemDiscription(String problemDiscription) {
        this.problemDiscription = problemDiscription;
    }


    public OrderModel(String phone, String model, String problem, String problemDiscription) {
        this.phone = phone;
        this.model = model;
        this.problem = problem;
        this.problemDiscription = problemDiscription;
    }
}
