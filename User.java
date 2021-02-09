package com.example.inframind;
public class User {
 public String
fullname,age,email,bodyTemperature,bloodPressure,respiration,glucose,heartRate,oxyg
enSaturation,electroCardiogram;
 public User(){
 }
 public User(String fullname, String age, String email, String temp, String
blood, String oxygen, String glucose, String heart, String hemoglobin, String
cardiogram){
 this.fullname = fullname;
 this.age = age;
 this.email = email;
 this.bodyTemperature = temp;
 this.bloodPressure = blood;
 this.respiration = oxygen;
 this.glucose = glucose;
 this.heartRate = heart;
 this.oxygenSaturation = hemoglobin;
 this.electroCardiogram = cardiogram;
 }
}