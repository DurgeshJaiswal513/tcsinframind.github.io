package com.example.inframind;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
public class RegisterUser extends AppCompatActivity implements View.OnClickListener
{

 private TextView banner, registerUser;
 private EditText editTextFullName, editTextAge, editTextEmail,
editTextPassword, editTextTemp, editTextBlood, editTextOxygen, editTextGlucose,
editTextHeart, editTextHemoglobin, editTextCardiogram;
 private ProgressBar progressBar;
 private FirebaseAuth mAuth;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_register_user);
 mAuth = FirebaseAuth.getInstance();

 banner = (TextView)findViewById(R.id.banner);
 banner.setOnClickListener(this);

 registerUser = (Button)findViewById(R.id.registerUser);
 registerUser.setOnClickListener(this);

 editTextFullName = (EditText)findViewById(R.id.fullname);
 editTextAge = (EditText)findViewById(R.id.age);
 editTextEmail = (EditText)findViewById(R.id.email);
 editTextPassword = (EditText)findViewById(R.id.password);
 editTextTemp = (EditText)findViewById(R.id.temp);
 editTextBlood = (EditText)findViewById(R.id.bloodPressure);
 editTextOxygen = (EditText)findViewById(R.id.oxygenLevel);
 editTextGlucose = (EditText)findViewById(R.id.glucoseLevel);
 editTextHeart = (EditText)findViewById(R.id.heartRate);
 editTextHemoglobin = (EditText)findViewById(R.id.Hemoglobin);
 editTextCardiogram = (EditText)findViewById(R.id.electroCardiogram);

 progressBar = (ProgressBar)findViewById(R.id.progressBar);
 }
 @Override
 public void onClick(View v) {
 switch(v.getId()){
 case R.id.banner:
 startActivity(new Intent(this, MainActivity.class));
 break;
 case R.id.registerUser:
 registerUser();
 break;
 }
 }
 private void registerUser() {
 String email = editTextEmail.getText().toString().trim();
 String password = editTextPassword.getText().toString().trim();
 String fullname = editTextFullName.getText().toString().trim();
 String age = editTextAge.getText().toString().trim();
 String temp = editTextTemp.getText().toString().trim();
 String blood = editTextBlood.getText().toString().trim();
 String oxygen = editTextOxygen.getText().toString().trim();
 String glucose = editTextGlucose.getText().toString().trim();
 String heart = editTextHeart.getText().toString().trim();
 String hemoglobin = editTextHemoglobin.getText().toString().trim();
 String cardiogram = editTextCardiogram.getText().toString().trim();
 if(fullname.isEmpty()){
 editTextFullName.setError("Full name is required");
 editTextFullName.requestFocus();
 return;
 }
 if(age.isEmpty()){
 editTextAge.setError("Age is required");
 editTextAge.requestFocus();
 return;
 }
 if(email.isEmpty()){
 editTextEmail.setError("Email is required");
 editTextEmail.requestFocus();
 return;
 }
 if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
 editTextEmail.setError("Please provide valid email!");
 editTextEmail.requestFocus();
 return;
 }
 if(password.isEmpty()){
 editTextPassword.setError("Password is required");
 editTextPassword.requestFocus();
 return;
 }
 if(password.length()<6){
 editTextPassword.setError("Min password length should be 6
characters!");
 editTextPassword.requestFocus();
 return;
 }
 if(temp.isEmpty()){
 editTextTemp.setError("This field is required");
 editTextTemp.requestFocus();
 return;
 }
 if(blood.isEmpty()){
 editTextBlood.setError("This field is required");
 editTextBlood.requestFocus();
 return;
 }
 if(oxygen.isEmpty()){
 editTextOxygen.setError("This field is required");
 editTextOxygen.requestFocus();
 return;
 }
 if(glucose.isEmpty()){
 editTextGlucose.setError("This field is required");
 editTextGlucose.requestFocus();
 return;
 }
 if(heart.isEmpty()){
 editTextHeart.setError("This field is required");
 editTextHeart.requestFocus();
 return;
 }
 if(hemoglobin.isEmpty()){
 editTextHemoglobin.setError("This field is required");
 editTextHemoglobin.requestFocus();
 return;
 }
 if(cardiogram.isEmpty()){
 editTextCardiogram.setError("This field is required");
 editTextCardiogram.requestFocus();
 return;
 }
 progressBar.setVisibility(View.VISIBLE);
 mAuth.createUserWithEmailAndPassword(email, password)
 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
 @Override
public void onComplete(@NonNull Task<AuthResult> task) {
 if(task.isSuccessful()){
 User user = new
User(fullname,age,email,temp,blood,oxygen,glucose,heart,hemoglobin,cardiogram);
 FirebaseDatabase.getInstance().getReference("Users")

.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
.setValue(user).addOnCompleteListener(new
OnCompleteListener<Void>() {
 @Override
public void onComplete(@NonNull Task<Void> task) {
 if(task.isSuccessful()){
 FirebaseUser user =
FirebaseAuth.getInstance().getCurrentUser();
 user.sendEmailVerification();

Toast.makeText(RegisterUser.this,"Verification link has been sent to your
email!",Toast.LENGTH_LONG).show();
 progressBar.setVisibility(View.GONE);
 // redirect to login layout
 }
else{
 Toast.makeText(RegisterUser.this,"Failed to
register, try again!",Toast.LENGTH_LONG).show();
 progressBar.setVisibility(View.GONE);
 }
 }
 });
 }
else{
 Toast.makeText(RegisterUser.this,"Someone already have
an account with this email id, Please enter your valid 
email!",Toast.LENGTH_LONG).show();
 progressBar.setVisibility(View.GONE);
 }
 }
 });
 }
}